package com.github.caijh.sample.drools;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class KieUtils {

    private static final String RULES_PATH = "rules/";

    private static final KieUtils INSTANCE = new KieUtils();

    private KieUtils() {
    }

    public static KieUtils getInstance() {
        return INSTANCE;
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
        Path rulePath = Paths.get(new ClassPathResource(RULES_PATH).getURI());
        for (Resource file : getRuleFiles()) {
            Path path = Paths.get(file.getURI());
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + rulePath.relativize(path).toString(),
                "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*");
    }

}
