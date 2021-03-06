package com.github.caijh.sample.drools.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.caijh.sample.drools.model.SoePoint;
import com.github.caijh.sample.drools.util.KieUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoePointController {


    @PostMapping(value = "/points")
    public List<SoePoint> accept(@RequestBody String reqBody) {
        List<SoePoint> points = new ArrayList<>();
        JSONArray tmp = JSON.parseObject(reqBody).getJSONArray("points");
        for (int i = 0; i < tmp.size(); i++) {
            String cvs = tmp.getString(i);
            String[] split = cvs.split(",");
            SoePoint point = new SoePoint();
            point.setReceiveTimestamp(split[0]);
            point.setStationId(Long.valueOf(split[1]));
            point.setPointId(split[2]);
            point.setStatus(Integer.valueOf(split[3]));
            point.setType(split[4]);
            point.setQuality(Integer.valueOf(split[5]));
            points.add(point);
        }
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        points.forEach(kieSession::insert);
        List<SoePoint> filterPoints = new ArrayList<>();
        kieSession.setGlobal("points", filterPoints);
        kieSession.fireAllRules();

        return filterPoints;
    }

}
