package com.joezhou.springboot2.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/async")
public class AsyncController {

    private AsyncTask asyncTask;

    @Autowired
    public AsyncController(AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }

    @RequestMapping("execute")
    public String execute() {
        long start = System.currentTimeMillis();
        asyncTask.taskA();
        asyncTask.taskB();
        asyncTask.taskC();
        long end = System.currentTimeMillis();
        return "execute() spend：" + (end - start);
    }

    @RequestMapping("execute-with-return")
    public String executeWithReturn() {
        long start = System.currentTimeMillis();
        Future<String> taskD = asyncTask.taskD();
        Future<String> taskE = asyncTask.taskE();
        Future<String> taskF = asyncTask.taskF();
        while (true) {
            if (taskD.isDone() && taskE.isDone() && taskF.isDone()) {
                System.out.println("all task is done...");
                break;
            }
        }
        long end = System.currentTimeMillis();
        return "executeWithReturn() spend：" + (end - start);
    }
}