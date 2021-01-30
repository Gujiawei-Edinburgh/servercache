package com.cache.server;


import com.cache.server.consumer.Consumer;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

public class CacheServer {

    @Autowired
    private Consumer consumer;

    public static void main(String[] args) {
        SpringApplication.run(CacheServer.class, args);
    }

    @PostConstruct
    public void start() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.submit(consumer);
    }

}
