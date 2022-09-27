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

    @Column(name = "c_processing")
    private boolean processing;

    @Column(name = "c_handled")
    private boolean handled;

    @Column(name = "c_failed_count")
    private int failedCount;

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

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    @Override
    public String toString() {
        return "ProvisioningData{" +
                "id=" + id +
                ", providerType=" + providerType +
                ", handled=" + handled +
                ", failedCount=" + failedCount +
                '}';
    }
}
