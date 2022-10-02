package com.sokeila.provider.service;

import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProviderDataService {

    private final ProvisioningDataRepository provisioningDataRepository;

    public ProviderDataService(ProvisioningDataRepository provisioningDataRepository) {
        this.provisioningDataRepository = provisioningDataRepository;
    }


    public void addRandomData() {
        Random random = new Random();
        int dataCount = random.nextInt(10) + 1;
        for(int i = 0; i < dataCount; i++) {
            provisioningDataRepository.saveAndFlush(generateData());
        }
    }

    public void addConsoleProviderData(int count) {
        for(int i = 0; i < count; i++) {
            provisioningDataRepository.saveAndFlush(generateData(ProviderType.CONSOLE));
        }
    }

    public void addSqlProviderData(int count) {
        for(int i = 0; i < count; i++) {
            provisioningDataRepository.saveAndFlush(generateData(ProviderType.SQL));
        }
    }

    private ProvisioningData generateData() {
        Random random = new Random();
        int type = random.nextInt(2);
        return generateData(type == 0 ? ProviderType.CONSOLE : ProviderType.SQL);
    }

    private ProvisioningData generateData(ProviderType providerType) {
        ProvisioningData data = new ProvisioningData();
        data.setProviderType(providerType);
        return data;
    }

    public void deleteAll() {
        provisioningDataRepository.deleteAll();
    }
}
