package com.pein.cloud.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pein on 2017/6/3.
 */
//@EnableEurekaClient //基于spring-cloud-netflix依赖，只能为eureka作用；
@EnableDiscoveryClient  //基于spring-cloud-commons依赖，并且在classpath中实现
@SpringBootApplication
@ComponentScan(basePackages = {"com.pein.cloud.program"})
public class EurekaProgramApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() { // equals to RestTemplate
        // restTemplate=new RestTemplate();
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaProgramApplication.class, args);
    }

}
