package com.server.cache.client;

import com.server.cache.message.AbstractMessage;
import com.server.cache.message.MailMessage;
import com.server.cache.message.WeChatMessage;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (int count = 0; count < executor.getPoolSize(); count++){
            AbstractMessage message;
            if (count % 2 == 0){
                message = new WeChatMessage();
            }else {
                message = new MailMessage();
            }
            MessageTask messageTask = MessageTask.builder()
                    .message(message)
                    .count(100)
                    .restTemplate(new RestTemplate())
                    .url("localhost:8080/send")
                    .build();
            executor.submit(messageTask);
        }
    }
}
