package com.cache.server.config;

import com.cache.server.handler.Handler;
import com.cache.server.message.MessageType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ConfigurationProperties("server-config")
@Data
@Component
public class AppConfig {
    private int capacity;
    private boolean isEvicted;
    private int timeIntervalInMs;
    private int maxRequest;

    @Bean
    public Map getHandlerMap() {
        return new ConcurrentHashMap<MessageType, Handler>(){{
            put(null,null);
        }};
    }
}
