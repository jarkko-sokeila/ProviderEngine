package com.sokeila.provider.providerengine.jpa;

import com.sokeila.provider.providerengine.ProviderType;

import javax.persistence.*;

@Entity
@Table(name = "t_provisioing_data")
public class ProvisioningData {
    @Id
    @Column(name = "c_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_provider_type")
    private ProviderType providerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProvisioningData{");
        sb.append("id=").append(id);
        sb.append(", providerType=").append(providerType);
        sb.append('}');
        return sb.toString();
    }
}
