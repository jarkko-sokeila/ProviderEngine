package com.sokeila.provider.providerengine;

import com.sokeila.provider.providerengine.console.ConsoleProviderEngine;
import com.sokeila.provider.providerengine.jpa.ProvisioningDataRepository;
import com.sokeila.provider.providerengine.sql.SqlProviderEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ProviderEngine implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ProviderEngine.class);

    public static int counter;

    private final TaskExecutor taskExecutor;
    private final ProvisioningDataRepository provisioningDataRepository;

    private final ConsoleProviderEngine consoleProviderEngine;
    private final SqlProviderEngine sqlProviderEngine;

    public ProviderEngine(TaskExecutor taskExecutor, ProvisioningDataRepository provisioningDataRepository, ConsoleProviderEngine consoleProviderEngine, SqlProviderEngine sqlProviderEngine) {
        this.taskExecutor = taskExecutor;
        this.provisioningDataRepository = provisioningDataRepository;
        this.consoleProviderEngine = consoleProviderEngine;
        this.sqlProviderEngine = sqlProviderEngine;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Application started with option names : {}", args.getOptionNames());
        log.info("Increment counter");
        counter++;

        taskExecutor.execute(consoleProviderEngine);
        taskExecutor.execute(sqlProviderEngine);

        /*Iterable<ProvisioningData> data = provisioningDataRepository.findAll();
        data.forEach(provisioningData -> log.debug("{}", provisioningData));

        Pageable pageable = PageRequest.of(0, 4);
        log.debug("Console data");
        List<ProvisioningData> consoleData = provisioningDataRepository.findProvisioningDataByProviderType(ProviderType.CONSOLE, pageable);
        consoleData.forEach(provisioningData -> log.debug("{}", provisioningData));*/
    }
}
