package com.pein.cloud.invoke.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pein on 2017/6/4.
 */
@RestController
@RequestMapping("/")
public class InvokeController {

    private static final Logger log = LoggerFactory.getLogger(InvokeController.class);
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("invoke")
    public String invokeHello(String a,String b){
        log.info("LOGGOER00020: to invoke hello . a = {},b = {}",a,b );
        return restTemplate.getForEntity("http://TS-EUREKA-CLIENT/hello?a=10&b=20", String.class).getBody();
    }
}
