package com.pein.cloud.config;

/**
 * Created by qiuw on 2017/9/18.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author qiuwei
 * @create 2017-09-18 13:58
 * 配置中心的服务端
 * 添加spring cloud bus,实现配置文件的热部署 /bus/refresh接口触发热部署
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
