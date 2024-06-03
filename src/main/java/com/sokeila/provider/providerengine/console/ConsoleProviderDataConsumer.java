package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.providerengine.AbstractProviderDataConsumer;
import com.sokeila.provider.providerengine.IProviderDataHandler;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;

public class ConsoleProviderDataConsumer extends AbstractProviderDataConsumer {

    public ConsoleProviderDataConsumer(ProvisioningDataRepository provisioningDataRepository, IProviderDataHandler consoleProviderDataHandler) {
        super(provisioningDataRepository, consoleProviderDataHandler);
    }
}
