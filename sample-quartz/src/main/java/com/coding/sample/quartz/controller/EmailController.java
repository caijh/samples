package com.coding.sample.quartz.controller;

import java.util.Arrays;

import com.coding.sample.quartz.entity.Email;
import com.coding.sample.quartz.service.EmailService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/add/email/schedule")
    public void addEmailSchedule() throws Exception {
        Email email = new Email();
        email.setTitle("test");
        email.setContent("test");
        email.setTo(Arrays.asList("me"));
        emailService.addEmailSchedule(email);
    }

    @GetMapping(value = "/update/email/schedule")
    public void updateEmailSchedule() throws Exception {
        Email email = new Email();
        email.setTitle("test");
        email.setContent("test");
        email.setTo(Arrays.asList("me"));
        emailService.updateEmailSchedule(email);
    }

    @GetMapping(value = "/delete/email/schedule")
    public void deleteEmailSchedule() throws SchedulerException {
        Email email = new Email();
        email.setTitle("test");
        email.setContent("test");
        email.setTo(Arrays.asList("me"));
        emailService.deleteEmailSchedule(email);
    }

}
