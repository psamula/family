package com.evaluationtask.FamilyMemberApp.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    private final String moduleName = "familymemberdb";

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