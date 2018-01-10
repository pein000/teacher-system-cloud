package com.pein.cloud.sleuth;

/**
 * Created by qiuw on 2017/9/25.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

/**
 * 链路追踪、日志收集
 *
 *  1 http方式：
 *     缺陷： 每次发送的时候涉及到连接和发送过程
 *            zipkin-server程序关闭或者重启过程中，因为客户端收集信息的发送采用http的方式会被丢失
 *  2 MQ方式：
 *      提高性能和可靠性
 *
 * @author qiuwei
 * @create 2017-09-25 9:27
 **/
@EnableEurekaClient
@SpringBootApplication
//@EnableZipkinServer
@EnableZipkinStreamServer  // 使用stream
public class EurekaSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSleuthApplication.class, args);
    }

}
