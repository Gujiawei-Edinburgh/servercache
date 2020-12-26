package com.server.cache.message;

import lombok.Data;

@Data
public abstract class AbstractMessage {
    private String type;
    private String content;

    public AbstractMessage(){

    }

    public String toString(){
        return type + "---" + content;
    }
}
