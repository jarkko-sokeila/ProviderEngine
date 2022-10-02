package com.sokeila.provider.ui;

import com.sokeila.provider.service.ProviderDataService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("/")
public class MainView extends VerticalLayout {

    private static final Logger log = LoggerFactory.getLogger(MainView.class);

    public MainView(ProviderDataService providerDataService) {
        add(new Text("Welcome to MainView."));

        IntegerField consoleDataCount = new IntegerField();
        Button addConsoleDataButton = new Button("Add console data", event -> {
            int count = consoleDataCount.getValue();
            providerDataService.addConsoleProviderData(count);
        });

        add(new HorizontalLayout(consoleDataCount, addConsoleDataButton));

        IntegerField sqlDataCount = new IntegerField();
        Button addSqlDataButton = new Button("Add Sql data", event -> {
            int count = sqlDataCount.getValue();
            providerDataService.addSqlProviderData(count);
        });

        add(new HorizontalLayout(sqlDataCount, addSqlDataButton));

        add(new Button("Delete all data", event -> {
            providerDataService.deleteAll();
        }));
    }
}
