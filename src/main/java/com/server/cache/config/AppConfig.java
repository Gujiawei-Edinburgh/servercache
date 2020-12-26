package com.server.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("server-config")
@Data
@Component
public class AppConfig {
    private int capacity;
    private boolean isEvicted;
    private int qps;
}
