package com.github.caijh.sample.zookeeper.controller;

import java.util.List;
import javax.inject.Inject;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ZookeeperController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperController.class.getName());

    @Inject
    private CuratorFramework client;

    @GetMapping(value = "/createZNode")
    public String createZNode(@RequestParam String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat == null) {
            client.create().forPath(path);

        }
        return "success";
    }

    @GetMapping(value = "/watch")
    public String watch(@RequestParam String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat == null) {
            return "path not found";
        }

        TreeCache cache = new TreeCache(client, path);
        cache.getListenable().addListener((client, event) -> {
            LOGGER.info(event.getType().toString());
            if (event.getData() != null) {
                LOGGER.info(event.getData().getPath());
            }
        });
        cache.start();
        return "success";
    }

    @GetMapping(value = "/existsZNode")
    public String existsZNode(@RequestParam String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);

        return stat != null ? "Exists" : "NotExists";
    }

    @GetMapping(value = "/deleteZNode")
    public String deleteZNode(@RequestParam String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat != null) {
            client.delete().forPath(path);
        }
        return "success";
    }

    @GetMapping(value = "/getChildren")
    public List<String> getChildren(@RequestParam String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat != null) {
            return client.getChildren().forPath(path);
        }
        return Lists.newArrayList();
    }

    @PostMapping(value = "/setData")
    public String setData(@RequestParam String path, @RequestParam MultipartFile file) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat == null) {
            client.create().forPath(path, file.getBytes());
        } else {
            client.setData().forPath(path, file.getBytes());
        }
        return "success";
    }

}
