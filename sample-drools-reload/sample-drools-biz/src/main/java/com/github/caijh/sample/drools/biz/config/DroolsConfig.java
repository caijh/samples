package com.github.caijh.sample.drools.biz.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieContainer kieContainer() {
        ReleaseId releaseId = getKieServices()
            .newReleaseId("com.github.caijh", "sample-drools-rule-kjar", "1.0-SNAPSHOT");

        KieContainer kieContainer = getKieServices().newKieContainer(releaseId);
        KieScanner kieScanner = getKieServices().newKieScanner(kieContainer);
        kieScanner.start(10000);

        return kieContainer;
    }

}
