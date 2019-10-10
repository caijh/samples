package com.github.caijh.sample.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloFeignController {

    @Autowired
    private HelloFeignClient helloFeignClient;

    // 服务消费者对位提供的服务
    @GetMapping(value = "/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return helloFeignClient.searchRepo(queryStr);
    }

}
