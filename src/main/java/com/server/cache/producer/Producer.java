package com.server.cache.producer;

import com.server.cache.message.AbstractMessage;
import com.server.cache.messagequeue.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private MessageQueue messageQueue;

    public void cacheMessage(AbstractMessage msg) throws InterruptedException {
        messageQueue.addMsg(msg);
    }
}
