package com.pein.cloud.ocr.pool;

/**
 * Created by qiuw on 2017/7/10.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author qiuwei
 * @create 2017-07-10 19:12
 **/
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor fixExecutor() {
        Executor executor = Executors.newFixedThreadPool(10);
        return executor;
    }

}
