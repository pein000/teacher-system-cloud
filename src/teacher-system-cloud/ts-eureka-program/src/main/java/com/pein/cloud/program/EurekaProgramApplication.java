package com.pein.cloud.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by pein on 2017/6/3.
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProgramApplication.class, args);
    }

}
