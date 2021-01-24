package com.server.cache.limiter;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {

    private Queue<Long> queue;

    private int timeIntervalInMs;

    public HitCounter(int timeIntervalInMs) {
        this.queue = new LinkedList<>();
        this.timeIntervalInMs = timeIntervalInMs;
    }

    public void addHit(long timeStamp) {
        queue.add(timeStamp);
    }

    public int getHits(long timeStamp) {
        while (!queue.isEmpty() && timeStamp - queue.peek() >= timeIntervalInMs) {
            queue.poll();
        }
        return queue.size();
    }

    public boolean isHit(long timeStamp, int maxRequest) {
        if (getHits(timeStamp) < maxRequest) {
            addHit(timeStamp);
            return true;
        }else {
            return false;
        }
    }
}

