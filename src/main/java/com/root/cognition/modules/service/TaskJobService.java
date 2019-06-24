package com.root.cognition.modules.service;


import com.root.cognition.modules.entity.Task;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 *
 * 定时任务
 * @author taoya
 */
public interface TaskJobService {

	Task get(Long id);

	List<Task> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(Task taskScheduleJob);

	int update(Task taskScheduleJob);

	int remove(Long id);

	int batchRemove(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
