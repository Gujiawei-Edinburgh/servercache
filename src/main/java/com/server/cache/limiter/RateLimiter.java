package com.server.cache.limiter;

import com.server.cache.message.AbstractMessage;
import com.server.cache.message.WeChatMessage;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private Map<String, HitCounter> map;

    private int maxRequest;

    private int timeIntervalInMs;

    public RateLimiter(int maxRequest, int timeIntervalInMs) {
        this.maxRequest = maxRequest;
        this.timeIntervalInMs = timeIntervalInMs;
        map = new HashMap<>();
    }

    public boolean tryAcquire(AbstractMessage message) {
        long currentTime = System.currentTimeMillis();
        if (message == null) {
            message = new WeChatMessage();
            message.setType("null message");
        }
        if (!map.containsKey(message)) {
            HitCounter hitCounter = new HitCounter(this.timeIntervalInMs);
            map.put(message.getType(), hitCounter);
            return hitCounter.isHit(currentTime, maxRequest);
        }else {
            return map.get(message.getType()).isHit(currentTime, maxRequest);
        }
    }
}
