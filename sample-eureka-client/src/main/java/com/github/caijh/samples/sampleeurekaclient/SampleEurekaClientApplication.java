package com.github.caijh.samples.sampleeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SampleEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleEurekaClientApplication.class, args);
    }

}
