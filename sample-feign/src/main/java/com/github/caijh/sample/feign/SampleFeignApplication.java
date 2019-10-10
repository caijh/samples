package com.github.caijh.sample.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SampleFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleFeignApplication.class, args);
    }

}
