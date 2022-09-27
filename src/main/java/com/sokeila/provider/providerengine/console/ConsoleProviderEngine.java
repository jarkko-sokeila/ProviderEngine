package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.ProviderDataConsumer;
import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningData;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ConsoleProviderEngine extends AbstractProviderEngine {

    public ConsoleProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, @Qualifier("consoleConsumer") ProviderDataConsumer consoleConsumer) {
        super(taskExecutor, provisioningDataRepository, consoleConsumer);
    }

    @Override
    protected ProviderType getProviderType() {
        return ProviderType.CONSOLE;
    }
}
