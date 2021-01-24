package com.server.cache.consumer;

import com.server.cache.limiter.RateLimiter;
import com.server.cache.messagequeue.MessageQueue;
import lombok.Builder;

@Builder
public class Consumer implements Runnable{

    private RateLimiter rateLimiter;

    private MessageQueue mq;


    public String consumeMsg(){
        if (rateLimiter.tryAcquire(mq.getQueue().peek())){
            System.out.println("-----try to consume message-----");
            try{
                mq.sendMsg();
            }catch (Exception e){
                System.out.println("*****Interrupted exception*****");
                return "fail";
            }
            System.out.println("-----finish consuming message-----");
            return "success";
        }
        return "fail";
    }

    @Override
    public void run() {
        while (true){
            consumeMsg();
        }
    }
}
