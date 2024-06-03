package com.sokeila.provider.providerengine.sql;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.IProviderDataConsumer;
import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SqlProviderEngine extends AbstractProviderEngine {

    private final SqlProviderDataHandler sqlProviderDataHandler;

    public SqlProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, SqlProviderDataHandler sqlProviderDataHandler) {
        super(taskExecutor, provisioningDataRepository);
        this.sqlProviderDataHandler = sqlProviderDataHandler;
    }

    @Override
    protected List<IProviderDataConsumer> getConsumers() {
        List<IProviderDataConsumer> consumers = new ArrayList<>();
        consumers.add(new SqlProviderDataConsumer(provisioningDataRepository, sqlProviderDataHandler));
        consumers.add(new SqlProviderDataConsumer(provisioningDataRepository, sqlProviderDataHandler));
        consumers.add(new SqlProviderDataConsumer(provisioningDataRepository, sqlProviderDataHandler));
        consumers.add(new SqlProviderDataConsumer(provisioningDataRepository, sqlProviderDataHandler));
        return consumers;
    }

    @Override
    protected ProviderType getProviderType() {
        return ProviderType.SQL;
    }
}
