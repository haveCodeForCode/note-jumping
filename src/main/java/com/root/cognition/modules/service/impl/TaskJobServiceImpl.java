package com.root.cognition.modules.service.impl;


import com.root.cognition.common.config.Constant;
import com.root.cognition.common.until.ScheduleJobUtils;
import com.root.cognition.modules.config.QuartzManager;
import com.root.cognition.modules.dao.TaskDao;
import com.root.cognition.modules.entity.ScheduleJob;
import com.root.cognition.modules.entity.Task;
import com.root.cognition.modules.service.TaskJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务实现类
 * @author taoya
 */
@Service
public class TaskJobServiceImpl implements TaskJobService {


	private TaskDao taskScheduleJobMapper;

	private QuartzManager quartzManager;

	@Autowired
	public void setQuartzManager(QuartzManager quartzManager) {
		this.quartzManager = quartzManager;
	}

	@Autowired
	public void setTaskScheduleJobMapper(TaskDao taskScheduleJobMapper) {
		this.taskScheduleJobMapper = taskScheduleJobMapper;
	}

	@Override
	public Task get(Long id) {
		return taskScheduleJobMapper.get(id);
	}

	@Override
	public List<Task> list(Map<String, Object> map) {
		return taskScheduleJobMapper.findList(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return taskScheduleJobMapper.count(map);
	}

	@Override
	public int save(Task taskScheduleJob) {
		taskScheduleJob.preInsert();
		return taskScheduleJobMapper.insert(taskScheduleJob);
	}

	@Override
	public int update(Task taskScheduleJob) {
		return taskScheduleJobMapper.update(taskScheduleJob);
	}

	@Override
	public int remove(Long id) {
		try {
			Task scheduleJob = get(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			return taskScheduleJobMapper.remove(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int batchRemove(Long[] ids) {
		for (Long id : ids) {
			try {
				Task scheduleJob = get(id);
				quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			} catch (SchedulerException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskScheduleJobMapper.batchRemove(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<Task> jobList = taskScheduleJobMapper.findList(new HashMap<String, Object>(16));
		for (Task scheduleJob : jobList) {
			if ("1".equals(scheduleJob.getJobStatus())) {
				ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
				quartzManager.addJob(job);
			}

		}
	}

	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		Task scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
		} else {
			if (!Constant.STATUS_RUNNING_START.equals(cmd)) {
			} else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
		}
		update(scheduleJob);
	}

	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		Task scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		update(scheduleJob);
	}

}
