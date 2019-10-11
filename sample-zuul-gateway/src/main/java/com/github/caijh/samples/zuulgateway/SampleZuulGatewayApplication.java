package com.github.caijh.samples.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class SampleZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleZuulGatewayApplication.class, args);
    }

}
