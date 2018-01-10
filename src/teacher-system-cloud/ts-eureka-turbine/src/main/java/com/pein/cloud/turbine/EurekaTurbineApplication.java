package com.pein.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by pein on 2017/6/3.
 * eureka集群监控(断路器聚合监控)
 * http://localhost:8751/hystrix ——> http://localhost:8751/turbine.stream
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
public class EurekaTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTurbineApplication.class, args);
    }

}
