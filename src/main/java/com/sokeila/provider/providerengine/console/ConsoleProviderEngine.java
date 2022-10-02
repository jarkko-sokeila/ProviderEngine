package com.sokeila.provider.providerengine.console;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.ProviderDataConsumer;
import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleProviderEngine extends AbstractProviderEngine {

    private final ConsoleProviderClient consoleProviderClient;

    public ConsoleProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, @Qualifier("consoleConsumer") ProviderDataConsumer consoleConsumer, ConsoleProviderClient consoleProviderClient) {
        super(taskExecutor, provisioningDataRepository, consoleConsumer);
        this.consoleProviderClient = consoleProviderClient;
    }

    @Override
    protected List<ProviderDataConsumer> getConsumers() {
        List<ProviderDataConsumer> consumers = new ArrayList<>();
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderClient));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderClient));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderClient));
        consumers.add(new ConsoleProviderDataConsumer(provisioningDataRepository, consoleProviderClient));
        return consumers;
    }

    @Override
    protected ProviderType getProviderType() {
        return ProviderType.CONSOLE;
    }
}
