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
    public void taskA() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1L);
            long end = System.currentTimeMillis();
            System.out.println("taskA spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void taskB() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(2L);
            long end = System.currentTimeMillis();
            System.out.println("taskB spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void taskC() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(3L);
            long end = System.currentTimeMillis();
            System.out.println("taskC spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public Future<String> taskD() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1L);
            long end = System.currentTimeMillis();
            System.out.println("taskD spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskD...");
    }

    @Async
    public Future<String> taskE() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(2L);
            long end = System.currentTimeMillis();
            System.out.println("taskE spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskE...");
    }

    @Async
    public Future<String> taskF() {
        try {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(3L);
            long end = System.currentTimeMillis();
            System.out.println("taskF spend: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskF...");
    }
}