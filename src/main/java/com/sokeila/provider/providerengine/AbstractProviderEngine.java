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

    protected final ProvisioningDataRepository provisioningDataRepository;

    private final TaskExecutor taskExecutor;
    private final BlockingQueue<ProvisioningData> blockingQueue = new LinkedBlockingQueue<>(8);

    public AbstractProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, ProviderDataConsumer providerDataConsumer) {
        this.taskExecutor = taskExecutor;
        this.provisioningDataRepository = provisioningDataRepository;
        //providerDataConsumer.setBlockingQueue(blockingQueue);
        //taskExecutor.execute(providerDataConsumer);
    }

    protected abstract List<ProviderDataConsumer> getConsumers();

    @Override
    public void run() {
        getConsumers().forEach(providerDataConsumer -> {
            providerDataConsumer.setBlockingQueue(blockingQueue);
            taskExecutor.execute(providerDataConsumer);
        });

        Pageable pageable = PageRequest.of(0, 16);
        do {
            try {
                List<ProvisioningData> provisioningData = provisioningDataRepository.findProvisioningDataByProviderTypeAndHandledFalseAndProcessingFalse(getProviderType(), pageable);
                if(provisioningData.size() > 0) {
                    log.debug("Load data for provisioning. Fetched count {}", provisioningData.size());
                }
                //provisioningData.forEach(provData -> log.debug("{}", provData));
                for (ProvisioningData provData : provisioningData) {
                    provData.setProcessing(true);
                    provisioningDataRepository.saveAndFlush(provData);
                    blockingQueue.put(provData);
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        } while (true);
    }

    protected abstract ProviderType getProviderType();
}
