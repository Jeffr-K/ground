package com.meshcraft.groundsearch.infrastructure.configs

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import org.opensearch.client.RestClient
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.OpenSearchTransport
import org.opensearch.client.transport.rest_client.RestClientTransport
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenSearchConfig {

    @Value("\${opensearch.host:localhost}")
    private lateinit var host: String

    @Value("\${opensearch.port:9200}")
    private var port: Int = 0

    @Value("\${opensearch.username:admin}")
    private lateinit var username: String

    @Value("\${opensearch.password:admin}")
    private lateinit var password: String

    @Value("\${opensearch.scheme:https}")
    private lateinit var scheme: String

    @Bean
    fun restClient(): RestClient {
        val credentialsProvider: CredentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials(username, password)
        )

        return RestClient.builder(
            HttpHost(host, port, scheme)
        ).setHttpClientConfigCallback { httpClientBuilder ->
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
        }.build()
    }

    @Bean
    fun openSearchClient(restClient: RestClient): OpenSearchClient {
        val mapper = JacksonJsonpMapper()
        val transport: OpenSearchTransport = RestClientTransport(restClient, mapper)
        return OpenSearchClient(transport)
    }
}