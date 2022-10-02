package com.sokeila.provider.providerengine.rest;

import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import com.sokeila.provider.service.ProviderDataService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("api")
public class ProviderController {

    private final ProviderDataService providerDataService;

    public ProviderController(ProviderDataService providerDataService) {
        this.providerDataService = providerDataService;
    }

    @PutMapping("/put-data")
    public void putData() {
        providerDataService.addRandomData();
    }
}
