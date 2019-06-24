package com.root.cognition.modules.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Worry
 * @version 2019/6/22
 */
public class JobTest implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int i = 0;
        i++;
        System.err.println(i);
    }
}
