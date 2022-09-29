package com.sokeila.provider.providerengine.rest;

import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("api")
public class ProviderController {

    private final ProvisioningDataRepository provisioningDataRepository;

    public ProviderController(ProvisioningDataRepository provisioningDataRepository) {
        this.provisioningDataRepository = provisioningDataRepository;
    }

    @PutMapping("/put-data")
    public void putData() {
        Random random = new Random();
        int dataCount = random.nextInt(10) + 1;
        for(int i = 0; i < dataCount; i++) {
            provisioningDataRepository.saveAndFlush(generateData());
        }
    }

    private ProvisioningData generateData() {
        Random random = new Random();
        int type = random.nextInt(2);
        ProvisioningData data = new ProvisioningData();
        data.setProviderType(ProviderType.CONSOLE);
        //data.setProviderType(type == 0 ? ProviderType.CONSOLE : ProviderType.SQL);
        return data;
    }
}
