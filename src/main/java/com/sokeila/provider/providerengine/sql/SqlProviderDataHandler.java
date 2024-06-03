package com.sokeila.provider.providerengine.sql;

import com.sokeila.provider.providerengine.IProviderDataHandler;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class SqlProviderDataHandler implements IProviderDataHandler {
    private final ProvisioningDataRepository provisioningDataRepository;

    public SqlProviderDataHandler(ProvisioningDataRepository provisioningDataRepository) {
        this.provisioningDataRepository = provisioningDataRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean provision(ProvisioningData provisioningData) {
        Random random = new Random();
        try {
            Thread.sleep(500);
            //Thread.sleep(random.nextInt(2000 - 1000) + 1000);
        } catch (InterruptedException e) {
        }

        provisioningData.setAccountName(UUID.randomUUID().toString());
        provisioningDataRepository.save(provisioningData);

        boolean result = random.nextBoolean();
        if (!result) {
            //throw new ProvisioningFailedException();
        }

        return true;
    }
}
