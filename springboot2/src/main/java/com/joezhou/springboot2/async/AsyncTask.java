package com.joezhou.springboot2.async;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
@Component
public class AsyncTask {

    @Async
    public Future<String> taskA() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1L);
            long end = System.currentTimeMillis();
            System.out.println("taskA spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskA done...");
    }

    @Async
    public Future<String> taskB() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(2L);
            long end = System.currentTimeMillis();
            System.out.println("taskB spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskB done...");
    }

    @Async
    public Future<String> taskC() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(3L);
            long end = System.currentTimeMillis();
            System.out.println("taskC spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskC done...");
    }
}