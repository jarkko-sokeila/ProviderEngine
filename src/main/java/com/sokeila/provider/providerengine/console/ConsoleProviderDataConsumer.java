package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.exception.ProvisioningFailedException;
import com.sokeila.provider.providerengine.ProviderDataConsumer;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

@Service
@Qualifier("consoleConsumer")
public class ConsoleProviderDataConsumer implements ProviderDataConsumer {
    private static final Logger log = LoggerFactory.getLogger(ConsoleProviderDataConsumer.class);
    private BlockingQueue<ProvisioningData> blockingQueue;

    private final ProvisioningDataRepository provisioningDataRepository;

    private final ConsoleProviderClient consoleProviderClient;

    public ConsoleProviderDataConsumer(ProvisioningDataRepository provisioningDataRepository, ConsoleProviderClient consoleProviderClient) {
        this.provisioningDataRepository = provisioningDataRepository;
        this.consoleProviderClient = consoleProviderClient;
    }

    @Override
    public void setBlockingQueue(BlockingQueue<ProvisioningData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ProvisioningData provisioningData = blockingQueue.take();
                try {
                    boolean status = consoleProviderClient.provision(provisioningData);

                    if (!status) {
                        provisioningData.setFailedCount(provisioningData.getFailedCount() + 1);
                    } else {
                        provisioningData.setHandled(true);
                    }
                    provisioningData.setProcessing(false);
                    provisioningDataRepository.saveAndFlush(provisioningData);
                    log.debug("Result: {}", provisioningData);
                } catch (Exception e) {
                    if(e instanceof ProvisioningFailedException) {
                        log.error("Provisioning failed");
                    } else {
                        log.error("Provisioning exception", e);
                    }

                    Optional<ProvisioningData> provisioningDataOpt = provisioningDataRepository.findById(provisioningData.getId());
                    if(provisioningDataOpt.isPresent()) {
                        provisioningData = provisioningDataOpt.get();
                        provisioningData.setFailedCount(provisioningData.getFailedCount() + 1);
                        provisioningData.setProcessing(false);
                        provisioningDataRepository.saveAndFlush(provisioningData);
                    }
                }
            } catch (Exception e) {
                log.error("Failed to take data from blockingQueue", e);
            }
        }
    }
}
