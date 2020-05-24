package com.github.caijh.sample.drools.config;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.data.Stat;
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
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.zookeeper.ZookeeperAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@AutoConfigureAfter({ZookeeperAutoConfiguration.class})
public class DroolsConfig implements EnvironmentAware, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(DroolsConfig.class);

    private static final String RULES_PATH = "rules/";
    private static final String ZK_ENABLED = "spring.cloud.zookeeper.enabled";
    private static final String ZK_PREFIX_NODE_PATH = "/rules";

    private Environment env;

    private ApplicationContext applicationContext;

    private KieBuilder kieBuilder;

    private CuratorFramework client;

    private volatile boolean init = false;


    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws Exception {
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
    public KieContainer kieContainer() throws Exception {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

        kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        init = true;

        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws Exception {
        return kieContainer().getKieBase();
    }


    @Bean
    @ConditionalOnMissingBean(KieSession.class)
    public KieSession kieSession() throws Exception {
        return kieContainer().newKieSession();
    }

    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

    private void loadRulesFromZK(KieFileSystem kieFileSystem) throws Exception {
        client = applicationContext.getBean(CuratorFramework.class);
        Stat stat = client.checkExists().forPath(ZK_PREFIX_NODE_PATH);
        if (stat == null) {
            client.create().forPath(ZK_PREFIX_NODE_PATH);
        }
        TreeCache treeCache = new TreeCache(client, ZK_PREFIX_NODE_PATH);

        treeCache.getListenable().addListener(newTreeCacheListener(kieFileSystem));
        treeCache.start();
    }

    private TreeCacheListener newTreeCacheListener(KieFileSystem kieFileSystem) {
        return (client, event) -> {
            boolean update = false;
            switch (event.getType()) {
                case NODE_ADDED:
                case NODE_UPDATED:
                    if (!ZK_PREFIX_NODE_PATH.equals(event.getData().getPath())
                        && event.getData() != null
                        && event.getData().getData() != null) {
                        update = true;
                        kieFileSystem.write(ResourceFactory.newByteArrayResource(event.getData().getData())
                                                           .setTargetPath(event.getData().getPath().substring(1)));
                    }
                    break;
                case NODE_REMOVED:
                    if (event.getData() != null) {
                        update = true;
                        kieFileSystem.delete(event.getData().getPath().substring(1));
                    }
                    break;
                default:
                    break;
            }
            if (update && init) {
                kieBuilder.buildAll();
            }
        };
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
