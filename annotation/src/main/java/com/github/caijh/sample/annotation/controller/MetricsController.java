package com.github.caijh.sample.annotation.controller;

import com.github.caijh.sample.annotation.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private static final Logger LOG = LoggerFactory.getLogger(MetricsController.class);

    @Metrics(name = "总客流")
    @GetMapping(value = "/test")
    public String test(@RequestParam(name = "stationId") Long stationId) {
        LOG.info("stationId:" + stationId);

        return "test";
    }

}
