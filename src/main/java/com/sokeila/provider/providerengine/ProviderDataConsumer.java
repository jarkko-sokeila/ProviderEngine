package com.sokeila.provider.providerengine;

import com.sokeila.provider.providerengine.jpa.ProvisioningData;

import java.util.concurrent.BlockingQueue;

public interface ProviderDataConsumer extends Runnable {
    void setBlockingQueue(BlockingQueue<ProvisioningData> blockingQueue);
}
