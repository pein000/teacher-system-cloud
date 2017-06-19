package com.pein.cloud.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by pein on 2017/6/8.
 */
@EnableEurekaClient
@SpringBootApplication
public class WechatEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatEurekaApplication.class, args);
    }

}
