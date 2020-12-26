package com.server.cache.message;

public class ResponseMessage extends AbstractMessage{

    public ResponseMessage(String type, String content){
        this.setType(type);
        this.setContent(content);
    }

    public String toString(){
        return this.getType() + "---" + this.getContent();
    }
}
