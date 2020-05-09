package com.github.caijh.sample.drools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.caijh.sample.drools.model.SoePoint;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
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

        KieContainer kieContainer = kieContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(point1);
        kieSession.insert(point2);
        kieSession.insert(point3);
        kieSession.insert(point4);
        kieSession.setGlobal("points", points);
        kieSession.fireAllRules();
        kieSession.dispose();
        Assert.notEmpty(points, "");

    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }


    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

}
