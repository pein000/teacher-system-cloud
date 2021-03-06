package com.pein.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author pein
 * @date 2017/6/3
 *
 * java -jar --spring.profiles.active=nodeA
 * java -jar --spring.profiles.active=nodeB
 * java -jar --spring.profiles.active=nodeC
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    /**
     * spring boot的标准入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
