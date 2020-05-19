package com.github.caijh.sample.drools.config;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import javax.inject.Inject;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class DroolsConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DroolsConfig.class);

    private static final String RULES_PATH = "rules/";
    private static final String ZK_ENABLED = "spring.cloud.zookeeper.enabled";
    private static final String ZK_PREFIX_NODE_PATH = "/rules";

    @Inject
    private Environment env;
    @Inject
    private CuratorFramework client;


    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();

        boolean isZKEnabled = env.getProperty(ZK_ENABLED, Boolean.class, false);
        if (isZKEnabled) {
            LOG.info("load rule from zk");
            loadRulesFromZK(kieFileSystem);

        } else {
            LOG.info("load rule from local");
            ClassPathResource ruleResource = new ClassPathResource(RULES_PATH);
            Path rulePath = Paths.get(ruleResource.getURI());
            for (Resource file : getRuleFiles()) {
                Path path = Paths.get(file.getURI());
                kieFileSystem.write(ResourceFactory
                    .newClassPathResource(RULES_PATH + rulePath.relativize(path).toString(), "UTF-8"));
            }
        }
        return kieFileSystem;
    }

    @Bean
    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }


    @Bean
    @ConditionalOnMissingBean(KieSession.class)
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

    private void loadRulesFromZK(KieFileSystem kieFileSystem) {
        TreeCache treeCache = new TreeCache(client, ZK_PREFIX_NODE_PATH);

        treeCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case NODE_ADDED:
                case NODE_UPDATED:
                    ResourceWrapper resourceWrapper = loadRule(event.getData().getPath(), event.getData().getData());
                    kieFileSystem.write(event.getData().getPath(), event.getData().getData());
                    break;
                case NODE_REMOVED:
                    removeRule(event.getData().getPath());
                    break;
                default:
                    break;
            }
        }, Executors.newSingleThreadExecutor());
    }

    private ResourceWrapper loadRule(String path, byte[] data) {
        org.kie.api.io.Resource resource = ResourceFactory.newByteArrayResource(data);
        return new ResourceWrapper(path, resource);
    }

    private void removeRule(String path) {
    }

    private static class ResourceWrapper {

        private String path;
        private org.kie.api.io.Resource resource;

        public ResourceWrapper(String path, org.kie.api.io.Resource resource) {
            this.path = path;
            this.resource = resource;
        }

    }

}
