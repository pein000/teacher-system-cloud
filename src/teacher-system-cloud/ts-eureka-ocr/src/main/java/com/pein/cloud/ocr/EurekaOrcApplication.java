package com.pein.cloud.ocr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by qiuwei on 2017/2/27.
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.pein.cloud.ocr"})
public class EurekaOrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaOrcApplication.class, args);
    }


}
