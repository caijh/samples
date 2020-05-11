package com.github.caijh.sample.drools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.caijh.sample.drools.model.SoePoint;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.util.Assert;

public class SoePointRuleTests {

    private static final String RULES_PATH = "rules/";

    @Test
    public void test() throws IOException {
        SoePoint point1 = new SoePoint();
        point1.setStationId(57L);
        point1.setPointId("4000096223");
        point1.setReceiveTimestamp("2018-09-26 14:13:05.798");
        point1.setQuality(1);
        point1.setType("MI");
        point1.setStatus(1);
        SoePoint point2 = new SoePoint();
        point2.setStationId(57L);
        point2.setPointId("4000096223");
        point2.setReceiveTimestamp("2018-09-26 14:13:05.798");
        point2.setQuality(1);
        point2.setType("AI");
        point2.setStatus(1);
        SoePoint point3 = new SoePoint();
        point3.setStationId(57L);
        point3.setPointId("4000096223");
        point3.setReceiveTimestamp("2018-09-26 14:13:05.798");
        point3.setQuality(3);
        point3.setType("MI");
        point3.setStatus(1);
        SoePoint point4 = new SoePoint();
        point4.setStationId(57L);
        point4.setPointId("4000096224");
        point4.setReceiveTimestamp("2018-09-26 14:13:05.798");
        point4.setQuality(1);
        point4.setType("MI");
        point4.setStatus(1);

        List<SoePoint> points = new ArrayList<>();

        KieContainer kieContainer = KieUtils.getInstance().kieContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(point1);
        kieSession.insert(point2);
        kieSession.insert(point3);
        kieSession.insert(point4);
        kieSession.setGlobal("points", points);
        kieSession.fireAllRules();
        kieSession.dispose();
        Assert.notEmpty(points, "");

        QueryResults results = kieSession.getQueryResults("query type=MI and quality==1");
        System.out.println("results size is " + results.size());
        for (QueryResultsRow row : results) {
            SoePoint point = (SoePoint) row.get("$point");
            System.out.println("SoePoint " + point.getPointId());
        }
        Assert.isTrue(points.size() == results.size(), "");
    }


}
