package com.notejumping.modules.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Worry
 * @version 2019/6/22
 */
public class JobToSms implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.err.println("执行了一次");
    }
}
