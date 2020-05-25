package com.github.caijh.sample.annotation;

public class MetricsTests {

    public void test() {

    }

    @Metrics(name = "总客流", params = {
        @MetricsParam(name = "stationId", valueType = Long.class, implicit = true),
        @MetricsParam(name = "startTime", valueType = String.class),
        @MetricsParam(name = "endTime", valueType = String.class),
        @MetricsParam(name = "step", valueType = String.class)
    })
    public void passengerFlow() {

    }

}
