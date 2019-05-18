package com.coding.sample.quartz.service;

import com.coding.sample.quartz.entity.Email;
import org.quartz.SchedulerException;

public interface EmailService {

    boolean addEmailSchedule(Email email) throws SchedulerException;

    void updateEmailSchedule(Email email) throws SchedulerException;


    void deleteEmailSchedule(Email email) throws SchedulerException;

}
