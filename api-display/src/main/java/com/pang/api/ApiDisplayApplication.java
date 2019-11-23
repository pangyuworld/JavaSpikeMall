package com.pang.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.pang.redis", "com.pang.api"})
@EnableEurekaClient
@EnableFeignClients
public class ApiDisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDisplayApplication.class, args);
    }


}
