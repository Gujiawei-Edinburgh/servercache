package com.cache.server.controller;

import com.cache.server.handler.Handler;
import com.cache.server.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RequestController {

    @Autowired
    private Map<MessageType, Handler> map;

    @PostMapping("/send")
    @ResponseBody
    public ResponMessage sendMessageToMQ(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }
}
