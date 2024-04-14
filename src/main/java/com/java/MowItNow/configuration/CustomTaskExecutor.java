package com.java.MowItNow.configuration;

import org.springframework.core.task.TaskExecutor;

public class CustomTaskExecutor implements TaskExecutor {

    @Override
    public void execute(Runnable task) {
        // Execute the task asynchronously
        new Thread(task).start();
    }
}