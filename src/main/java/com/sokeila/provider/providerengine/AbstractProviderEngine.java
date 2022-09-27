package com.sokeila.provider.providerengine;

import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractProviderEngine implements Runnable { // implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(AbstractProviderEngine.class);

    private final ProvisioningDataRepository provisioningDataRepository;
    private final BlockingQueue<ProvisioningData> blockingQueue = new LinkedBlockingQueue<>(2);

    public AbstractProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, ProviderDataConsumer providerDataConsumer) {
        this.provisioningDataRepository = provisioningDataRepository;
        providerDataConsumer.setBlockingQueue(blockingQueue);
        taskExecutor.execute(providerDataConsumer);
    }

    @Override
    public void run() {
        Pageable pageable = PageRequest.of(0, 4);
        try {
            do {
                List<ProvisioningData> provisioningData = provisioningDataRepository.findProvisioningDataByProviderTypeAndHandledFalseAndProcessingFalse(getProviderType(), pageable);
                log.debug("Load data for provisioning. Fetched count {}", provisioningData.size());
                //provisioningData.forEach(provData -> log.debug("{}", provData));
                for(ProvisioningData provData: provisioningData) {
                    provData.setProcessing(true);
                    provisioningDataRepository.saveAndFlush(provData);
                    blockingQueue.put(provData);
                }
                Thread.sleep(1000);
            } while (true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /*@Override
    public void run(ApplicationArguments args) {
        Pageable pageable = PageRequest.of(0, 4);
        try {
            do {
                List<ProvisioningData> provisioningData = provisioningDataRepository.findProvisioningDataByProviderTypeAndHandledFalseAndProcessingFalse(getProviderType(), pageable);
                log.debug("Load data for provisioning. Fetched count {}", provisioningData.size());
                //provisioningData.forEach(provData -> log.debug("{}", provData));
                for(ProvisioningData provData: provisioningData) {
                    provData.setProcessing(true);
                    provisioningDataRepository.saveAndFlush(provData);
                    blockingQueue.put(provData);
                }
                Thread.sleep(1000);
            } while (true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }*/

    public BlockingQueue<ProvisioningData> getBlockingQueue() {
        return blockingQueue;
    }

    protected abstract ProviderType getProviderType();
}
