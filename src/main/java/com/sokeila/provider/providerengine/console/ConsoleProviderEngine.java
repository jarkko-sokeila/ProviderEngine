package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.IProviderDataConsumer;
import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleProviderEngine extends AbstractProviderEngine {

    private final ConsoleProviderDataHandler consoleProviderDataHandler;

    public ConsoleProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, ConsoleProviderDataHandler consoleProviderDataHandler) {
        super(taskExecutor, provisioningDataRepository);
        this.consoleProviderDataHandler = consoleProviderDataHandler;
    }

    @Override
    protected List<IProviderDataConsumer> getConsumers() {
        List<IProviderDataConsumer> consumers = new ArrayList<>();
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderDataHandler));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderDataHandler));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderDataHandler));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderDataHandler));
        return consumers;
    }

    @Override
    protected ProviderType getProviderType() {
        return ProviderType.CONSOLE;
    }
}
