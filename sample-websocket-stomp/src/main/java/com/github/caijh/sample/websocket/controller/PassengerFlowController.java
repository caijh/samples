package com.github.caijh.sample.websocket.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSONObject;
import com.github.caijh.sample.websocket.model.FlowNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PassengerFlowController {

    private Random random = new Random(System.currentTimeMillis());
    private List<FlowNum> realTimeFlowNum = new ArrayList<>();
    private List<FlowNum> guessFlowNum = new ArrayList<>();
    private JSONObject data = new JSONObject();

    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping(value = "/flow/data")
    @ResponseBody
    public String getFlowData() {
        return data.toJSONString();
    }

    @PostConstruct
    private void broadcastTimePeriodically() {
        data.put("realTimeFlowNum", realTimeFlowNum);
        data.put("guessFlowNum", guessFlowNum);

        taskScheduler.scheduleAtFixedRate(this::broadcastUpdated, 10000);
    }

    private void broadcastUpdated() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String date = dateFormat.format(new Date());
        realTimeFlowNum.add(new FlowNum(date, random.nextInt(10000)));
        guessFlowNum.add(new FlowNum(date, random.nextInt(10000)));
        simpMessagingTemplate.convertAndSend("/topic/flow", data);
    }

}
