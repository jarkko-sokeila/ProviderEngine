package com.sokeila.provider.providerengine.sql;

import com.sokeila.provider.providerengine.AbstractProviderDataConsumer;
import com.sokeila.provider.providerengine.IProviderDataHandler;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;

public class SqlProviderDataConsumer extends AbstractProviderDataConsumer {

    public SqlProviderDataConsumer(ProvisioningDataRepository provisioningDataRepository, IProviderDataHandler sqlProviderDataHandler) {
        super(provisioningDataRepository, sqlProviderDataHandler);
    }
}
