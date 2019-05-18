package com.coding.sample.quartz.service.impl;

import com.coding.sample.quartz.entity.Email;
import com.coding.sample.quartz.schedule.EmailJob;
import com.coding.sample.quartz.service.EmailService;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public boolean addEmailSchedule(Email email) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", email);
        JobDetail jobDetail = JobBuilder.newJob()
                                        .ofType(EmailJob.class)
                                        .withIdentity(email.getTitle(), "email-jobs")
                                        .usingJobData(jobDataMap)
                                        .build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/30 * * * * ?");
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(email.getTitle(), "email-triggers")
                                        .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        return false;
    }

    @Override
    public void updateEmailSchedule(Email email) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(email.getTitle(), "email-triggers");
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/59 * * * * ?");
        Trigger newTrigger = TriggerBuilder.newTrigger()
                                           .withIdentity(email.getTitle(), "email-triggers")
                                           .withSchedule(cronScheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    @Override
    public void deleteEmailSchedule(Email email) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(email.getTitle(), "email-triggers");
        scheduler.unscheduleJob(triggerKey);
    }

}
