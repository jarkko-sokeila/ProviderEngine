package com.sokeila.provider.providerengine.jpa;

import com.sokeila.provider.providerengine.ProviderType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProvisioningDataRepository extends JpaRepository<ProvisioningData, Long> {
    List<ProvisioningData> findProvisioningDataByProviderType(ProviderType providerType, Pageable pageable);
    List<ProvisioningData> findProvisioningDataByProviderTypeAndHandledFalseAndProcessingFalse(ProviderType providerType, Pageable pageable);
}
