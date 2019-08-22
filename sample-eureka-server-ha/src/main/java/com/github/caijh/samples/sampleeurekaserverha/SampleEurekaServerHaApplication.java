package com.github.caijh.samples.sampleeurekaserverha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SampleEurekaServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleEurekaServerHaApplication.class, args);
    }

}
