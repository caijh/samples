package com.github.caijh.sample.prometheus.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.caijh.sample.prometheus.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws UnknownHostException {
        final String hostName = InetAddress.getLocalHost().getCanonicalHostName();
        helloService.inc();
        return "Hello from " + hostName + "!\nCounter value: " + helloService.value();
    }

}
