package com.github.caijh.sample.drools.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.caijh.sample.drools.util.KieUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.data.Stat;
import org.drools.compiler.kie.builder.impl.MemoryKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
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

@Configuration
@AutoConfigureAfter({ZookeeperAutoConfiguration.class})
public class DroolsConfig implements EnvironmentAware, ApplicationContextAware, InitializingBean {

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
            Path rulePath = Paths.get(new ClassPathResource(RULES_PATH).getURI());
            Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:" + RULES_PATH + "**/*.*");
            for (Resource file : resources) {
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

    private KieContainer kieContainer() throws Exception {
        final KieRepository kieRepository = getKieServices().getRepository();

        KieModule kieModule = new MemoryKieModule(kieRepository.getDefaultReleaseId());

        kieRepository.addKieModule(kieModule);

        kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }

        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
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
            if (update) {
                KieUtils.setKieContainer(kieContainer());
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

    @Override
    public void afterPropertiesSet() throws Exception {
        KieUtils.setKieContainer(kieContainer());
    }

}
