package com.chatApp.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

    @Autowired
    private AzureKeyVaultConfig vaultConfig;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(vaultConfig.getDbUrl())
                .username(vaultConfig.getDbUsername())
                .password(vaultConfig.getDbPassword())
                .build();
    }
}
