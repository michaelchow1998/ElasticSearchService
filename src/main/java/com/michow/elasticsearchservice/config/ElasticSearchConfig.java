package com.michow.elasticsearchservice.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {

    private String schema;

    private String address;

    private int connectTimeout;

    private int socketTimeout;

    private int connectionRequestTimeout;

    private int maxConnectNum;

    private int maxConnectPerRoute;

    private String index;

    private String username;

    private String passwd;
}
