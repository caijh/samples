package com.github.caijh.sample.prometheus.service.impl;

import com.github.caijh.sample.prometheus.service.HelloService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    private final Counter counter;

    HelloServiceImpl(CollectorRegistry registry) {
        this.counter = Counter.build("hello_http_requests_total", "Total requests.")
            .register(registry);
    }

    @Override
    public void inc() {
        counter.inc();
    }

    @Override
    public double value() {
        return counter.get();
    }
}
