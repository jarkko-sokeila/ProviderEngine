package com.sokeila.provider.providerengine.jpa;

import com.sokeila.provider.providerengine.ProviderType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvisioningDataRepository extends CrudRepository<ProvisioningData, Long> {
    List<ProvisioningData> findProvisioningDataByProviderType(ProviderType providerType);
}
