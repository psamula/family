package com.evaluationtask.FamilyApp.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/* A configuration class which facilitates automatic creation of two different flyway_schema_history tables for
    two different schemas located in the same database */
@Configuration
public class FlywayConfig {

    private final String moduleName = "familydb";

    private final String baseScriptLocation = "classpath:db.migration.";

    @Autowired
    public void migrate(DataSource dataSource) {
        Flyway.configure()
                .dataSource(dataSource)
                .schemas(moduleName.toLowerCase())
                .locations(baseScriptLocation + moduleName.toLowerCase())
                .load()
                .migrate();
    }
}