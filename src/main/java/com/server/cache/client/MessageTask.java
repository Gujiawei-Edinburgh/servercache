package com.server.cache.client;

import com.server.cache.message.AbstractMessage;
import lombok.Builder;
import org.springframework.web.client.RestTemplate;

@Builder
public class MessageTask implements Runnable{

    private AbstractMessage message;

    private RestTemplate restTemplate;

    private int count;

    private String url;

    public AbstractMessage postMsg(){
        AbstractMessage resp;
        try{
            resp = restTemplate.postForObject(url, message, message.getClass());
        }catch (Exception e){
            System.out.println("RestClientException");
            return null;
        }
        return resp;
    }

    @Override
    public void run() {
        for (int index = 0; index < count; index++){
            try{
                postMsg();
                Thread.sleep(10);
            }catch (Exception e){
                System.out.println("Interrupted exception");
            }
        }
    }
}
