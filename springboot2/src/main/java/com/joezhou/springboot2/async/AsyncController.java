package com.joezhou.springboot2.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
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
    public String execute() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> taskA = asyncTask.taskA();
        Future<String> taskB = asyncTask.taskB();
        Future<String> taskC = asyncTask.taskC();
        while (true) {
            if (taskA.isDone() && taskB.isDone() && taskC.isDone()) {
                System.out.println("all task is done...");
                System.out.println("taskA return: " + taskA.get());
                System.out.println("taskB return: " + taskB.get());
                System.out.println("taskC return: " + taskC.get());
                break;
            }
        }
        long end = System.currentTimeMillis();
        return "total spend：" + (end - start);
    }
}