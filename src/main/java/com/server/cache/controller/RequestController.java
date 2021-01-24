package com.server.cache.controller;

import com.server.cache.message.RequestMessage;
import com.server.cache.message.ResponseMessage;
import com.server.cache.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {

    @Autowired
    private Producer producer;

    @PostMapping("/send")
    public @ResponseBody
    ResponseMessage sendMsg(@RequestBody RequestMessage msg){
        try{
            producer.cacheMessage(msg);
        }catch (Exception e){
            System.out.println("Interrupted exception");
            return new ResponseMessage("response", "fail");
        }
        return new ResponseMessage("response", "success");
    }
}
