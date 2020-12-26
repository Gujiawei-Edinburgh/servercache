package com.server.cache.messagequeue;

import com.server.cache.config.AppConfig;
import com.server.cache.message.AbstractMessage;
import com.server.cache.message.ResponseMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@Component
public class MessageQueue {

    @Autowired
    private AppConfig appConfig;

    private BlockingQueue<AbstractMessage> queue;

    private int capacity;

    private boolean isEvicted;

    public MessageQueue(){

    }

    @PostConstruct
    public void createMQ(){
        capacity = appConfig.getCapacity();
        isEvicted = appConfig.isEvicted();
        queue = new LinkedBlockingQueue(capacity);
    }

    public String addMsg(AbstractMessage msg) throws InterruptedException {
        if (queue.size() >= capacity){
            System.out.println("message queue is full");
            if (isEvicted){
                queue.take();
                queue.put(msg);
                System.out.println("the oldest message has been evicted");
            }else {
                System.out.println("the youngest message has been dropped");
            }
            return "eviction/rejection has been done";
        }else {
            queue.put(msg);
            return "success";
        }
    }

    public AbstractMessage sendMsg() throws InterruptedException {
        AbstractMessage resp = new ResponseMessage("resp", queue.take().toString());
        System.out.println(resp.toString());
        return resp;
    }


}
