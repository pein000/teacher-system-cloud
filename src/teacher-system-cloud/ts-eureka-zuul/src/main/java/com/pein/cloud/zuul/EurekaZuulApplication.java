package com.pein.cloud.zuul;

/**
 * Created by qiuw on 2017/7/12.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author qiuwei
 * @create 2017-07-12 19:24
 **/
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaZuulApplication.class, args);
    }

}
