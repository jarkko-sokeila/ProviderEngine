package com.sokeila.provider;

import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

@SpringBootApplication
public class ProviderEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderEngineApplication.class, args);
    }

    @Bean
    @Order(value=1)
    public CommandLineRunner init(ApplicationContext ctx, ProvisioningDataRepository provisioningDataRepository) {
        return args -> {
            List<ProvisioningData> provisioningData = provisioningDataRepository.findAll();
            provisioningData.forEach(provData -> {
                provData.setProcessing(false);
                provData.setHandled(false);
                provData.setFailedCount(0);
                provData.setAccountName(null);
            });
            provisioningDataRepository.saveAllAndFlush(provisioningData);
        };
    }
}
