package com.notejumping.common.until;

import com.notejumping.modules.entity.ScheduleJob;
import com.notejumping.modules.entity.Task;

/**
 * 定时任务工具类
 * @author taoya
 */
public class ScheduleJobUtils {

	/**
	 * 转换为定时任务可用实体
	 * @param scheduleJobEntity
	 * @return
	 */
	public static ScheduleJob entityToData(Task scheduleJobEntity) {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
		scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
		scheduleJob.setDescription(scheduleJobEntity.getDescription());
		scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
		scheduleJob.setJobName(scheduleJobEntity.getJobName());
		scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
		scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
		scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
		scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
		return scheduleJob;
	}
}
