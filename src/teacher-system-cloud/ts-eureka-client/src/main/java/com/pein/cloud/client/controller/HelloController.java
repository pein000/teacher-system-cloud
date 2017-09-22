package com.pein.cloud.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pein on 2017/6/4.
 */
@RestController
@RequestMapping("/")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("hello")
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(Integer a, Integer b) {
        LOGGER.info("LOGGOER00000: to hello : a = {}, b = {}", a, b);
        if(a < 10){
            throw new RuntimeException();
        }
        return String.valueOf(a + b);
    }

    public String helloError(Integer a,Integer b) {
        return "hello";
    }
}
