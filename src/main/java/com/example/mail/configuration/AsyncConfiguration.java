package com.example.mail.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import java.util.concurrent.Executor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * <p>
 * this configuration handle the asynchronous messages(exemple Email) instantly,
 * since the SMTP server does not handle everything instantly
 * <\p>
 * <br>
 *
 *
 * created by  eric.nyandwi on Feb,13/02/2019
 */

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration extends AsyncConfigurerSupport {

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("send-mailer-");
        executor.initialize();
        return executor;
    }
}
