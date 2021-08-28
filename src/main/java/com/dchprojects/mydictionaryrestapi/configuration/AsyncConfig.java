package com.dchprojects.mydictionaryrestapi.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private static final String TASK_EXECUTOR_DEFAULT = "taskExecutor";
    private static final String TASK_EXECUTOR_NAME_PREFIX_DEFAULT = "taskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_REPOSITORY = "repositoryTaskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_CONTROLLER = "controllerTaskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_SERVICE = "serviceTaskExecutor-";

    public static final String TASK_EXECUTOR_REPOSITORY = "repositoryTaskExecutor";
    public static final String TASK_EXECUTOR_SERVICE = "serviceTaskExecutor";
    public static final String TASK_EXECUTOR_CONTROLLER = "controllerTaskExecutor";

    @Override
    @Bean(name = TASK_EXECUTOR_DEFAULT)
    public Executor getAsyncExecutor() {
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_DEFAULT);
    }

    @Bean(name = TASK_EXECUTOR_REPOSITORY)
    public Executor getRepositoryAsyncExecutor() {
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_REPOSITORY);
    }

    @Bean(name = TASK_EXECUTOR_SERVICE)
    public Executor getServiceAsyncExecutor() {
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_SERVICE);
    }

    @Bean(name = TASK_EXECUTOR_CONTROLLER)
    public Executor getControllerAsyncExecutor() {
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_CONTROLLER);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    private Executor newTaskExecutor(final String taskExecutorNamePrefix) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix(taskExecutorNamePrefix);
        return executor;
    }

}
