package com.pang.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.pang.redis", "com.pang.listener"})
@EnableEurekaClient
@EnableFeignClients
public class RedisListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisListenerApplication.class, args);
    }

}
