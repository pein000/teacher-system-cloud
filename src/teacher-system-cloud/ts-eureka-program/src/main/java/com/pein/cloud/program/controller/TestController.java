package com.pein.cloud.program.controller;

/**
 * Created by qiuw on 2017/9/20.
 */

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pein.cloud.program.config.ProgramConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 测试用
 *
 * @author qiuwei
 * @create 2017-09-20 14:02
 **/
@RestController
@RequestMapping("/test/")
public class TestController {

    private Logger LOGGER = LoggerFactory.getLogger(IdentifyController.class);

    @Autowired
    private ProgramConfig programConfig;

    @RequestMapping("config")
    public String config() {
        LOGGER.info("the program.hello value = {}. ", programConfig.getHelloProgram());
        return programConfig.getHelloProgram();
    }

    @RequestMapping("hystrix_one")
    @HystrixCommand(fallbackMethod="hystrixError") //熔断器配置，失败则调用hystrixError()方法
    public String hystrixOne() {
        if (new Random().nextInt(4) % 4 < 2) {
            throw new RuntimeException("模拟异常one");
        }
        return "hystrix one";
    }


    @RequestMapping("hystrix_Two")
    @HystrixCommand(fallbackMethod="hystrixError") //熔断器配置，失败则调用hystrixError()方法
    public String hystrixTwo() {
        return "hystrix one";
    }

    public String hystrixError() {
        return "hystrix error";
    }

}
