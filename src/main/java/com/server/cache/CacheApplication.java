package com.server.cache;

import com.google.common.util.concurrent.RateLimiter;
import com.server.cache.consumer.Consumer;
import com.server.cache.messagequeue.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class CacheApplication {

    @Autowired
    private MessageQueue messageQueue;

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @PostConstruct
    public void createConsumer(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        Consumer consumer = Consumer.builder()
                .rateLimiter(RateLimiter.create(10))
                .mq(messageQueue)
                .build();
        executor.submit(consumer);
    }

}
