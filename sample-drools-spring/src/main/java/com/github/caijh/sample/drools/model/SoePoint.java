package com.github.caijh.sample.drools.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SoePoint {

    private String receiveTimestamp;

    private Long stationId;

    private String pointId;

    private Integer status;

    private String type;

    private Integer quality;

}
