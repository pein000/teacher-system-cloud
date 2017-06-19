package com.pein.cloud.client.controller;

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
    public String hello(String a, String b) {
        LOGGER.info("LOGGOER00000: to hello : a = {}, b = {}", a, b);
        return a + b;
    }

}
