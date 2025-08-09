package com.chatApp.project.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfig {

    @Bean
    public BlobContainerClient blobContainerClient() {
        // Storage account name and container name (no secrets here)
        String endpoint = "https://chatappstore.blob.core.windows.net";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new DefaultAzureCredentialBuilder().build()) // Uses CLI login or Managed Identity
                .buildClient();

        String containerName = "attachments";
        return blobServiceClient.getBlobContainerClient(containerName);
    }
}
