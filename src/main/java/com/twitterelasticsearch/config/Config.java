package com.twitterelasticsearch.config;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;
import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.twitterelasticsearch.repository")
@ComponentScan(basePackages = { "com.twitterelasticsearch.service" })
@PropertySource("classpath:/application.properties")
public class Config {
    @Value("${elasticsearch.server.url}")
    public String elastisearchServerUrl;
    @Value("${elasticsearch.server.port}")
    public Integer elastisearchServerPort;
    @Value("${elasticsearch.index.name}")
    public String elastisearchIndexName;

    @Value("${twitter.consumerKey}")
    public String twitterConsumerKey;
    @Value("${twitter.consumerSecret}")
    public String twitterConsumerSecret;
    @Value("${twitter.accessToken}")
    public String twitterAccessToken;
    @Value("${twitter.accessTokenSecret}")
    public String twitterAccessTokenSecret;

    @Bean
    public Client client() {
        try {
            return TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elastisearchServerUrl), elastisearchServerPort));
        } catch (final IOException ioex) {
            throw new RuntimeException();
        }
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }

    @Bean
    public Authentication twitterAuthentication() {
        return new OAuth1(twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);
    }

    @Bean
    public ClientBuilder twitterClientBuilder() {
        return new ClientBuilder().hosts(Constants.STREAM_HOST).authentication(twitterAuthentication());
    }

    @Bean
    public String elastisearchIndexName() {
        return elastisearchIndexName;
    }
}
