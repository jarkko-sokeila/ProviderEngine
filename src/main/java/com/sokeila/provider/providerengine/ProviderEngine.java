package com.sokeila.provider.providerengine;

import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProviderEngine implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ProviderEngine.class);

    public static int counter;

    private final ProvisioningDataRepository provisioningDataRepository;

    public ProviderEngine(ProvisioningDataRepository provisioningDataRepository) {
        this.provisioningDataRepository = provisioningDataRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Application started with option names : {}", args.getOptionNames());
        log.info("Increment counter");
        counter++;

        Iterable<ProvisioningData> data = provisioningDataRepository.findAll();
        data.forEach(provisioningData -> log.debug("{}", provisioningData));
    }
}
