package com.coding.sample.quartz.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.coding.sample.quartz.entity.Email;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class EmailJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Email email = (Email) jobExecutionContext.getMergedJobDataMap().get("email");
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logger.info(email.getTitle());
    }

}
