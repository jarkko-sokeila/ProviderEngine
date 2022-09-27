package com.sokeila.provider.providerengine.sql;

import com.sokeila.provider.providerengine.AbstractProviderEngine;
import com.sokeila.provider.providerengine.ProviderDataConsumer;
import com.sokeila.provider.providerengine.ProviderType;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SqlProviderEngine extends AbstractProviderEngine {

    public SqlProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, @Qualifier("sqlConsumer") ProviderDataConsumer sqlConsumer) {
        super(taskExecutor, provisioningDataRepository, sqlConsumer);
    }

    @Override
    protected ProviderType getProviderType() {
        return ProviderType.SQL;
    }
}
