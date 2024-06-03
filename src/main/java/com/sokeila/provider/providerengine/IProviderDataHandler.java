package com.sokeila.provider.providerengine;

import com.sokeila.provider.providerengine.jpa.ProvisioningData;

public interface IProviderDataHandler {
    boolean provision(ProvisioningData provisioningData);
}
