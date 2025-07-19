package com.chatApp.project.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureKeyVaultConfig {

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    @PostConstruct
    public void loadSecretsFromKeyVault() {
        String keyVaultUrl = "https://chatapp-keyvault.vault.azure.net/";

        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        this.dbUrl = "jdbc:postgresql://chatapp-postgres.postgres.database.azure.com:5432/postgres?sslmode=require";
        this.dbUsername = secretClient.getSecret("DbAdminUsername").getValue();
        this.dbPassword = secretClient.getSecret("DbAdminPassword").getValue();
    }
}
