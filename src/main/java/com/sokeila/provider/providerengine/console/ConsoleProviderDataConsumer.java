package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.ProviderDataConsumer;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Qualifier("consoleConsumer")
public class ConsoleProviderDataConsumer implements ProviderDataConsumer {
    private static final Logger log = LoggerFactory.getLogger(ConsoleProviderDataConsumer.class);
    private BlockingQueue<ProvisioningData> blockingQueue;

    private final ProvisioningDataRepository provisioningDataRepository;

    public ConsoleProviderDataConsumer(ProvisioningDataRepository provisioningDataRepository) {
        this.provisioningDataRepository = provisioningDataRepository;
    }

    @Override
    public void setBlockingQueue(BlockingQueue<ProvisioningData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                ProvisioningData provisioningData = blockingQueue.take();
                Thread.sleep(random.nextInt(2000 - 1000) + 1000);
                int failed = random.nextInt(3);
                if(failed == 1) {
                    provisioningData.setFailedCount(provisioningData.getFailedCount() + 1);
                } else {
                    provisioningData.setHandled(true);
                }
                provisioningData.setProcessing(false);
                provisioningDataRepository.saveAndFlush(provisioningData);
                log.debug("Result: {}", provisioningData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
