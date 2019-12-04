package com.notejumping.modules.controller;


import com.notejumping.common.until.PageUtils;
import com.notejumping.common.until.Query;
import com.notejumping.common.until.ResultMap;
import com.notejumping.modules.entity.Task;
import com.notejumping.modules.service.TaskJobService;
import com.notejumping.system.persistence.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务前端控制器
 * @author 1024
 * @date 2017-09-26 20:53:48
 */
@Controller
@RequestMapping("/modules/job")
public class JobController extends BaseController {

	private TaskJobService taskScheduleJobService;

	@Autowired
	public void setTaskScheduleJobService(TaskJobService taskScheduleJobService) {
		this.taskScheduleJobService = taskScheduleJobService;
	}

	@GetMapping()
	String taskScheduleJob() {
		return "modules/job/job";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<Task> taskScheduleJobList = taskScheduleJobService.list(query);
		int total = taskScheduleJobService.count(query);
		PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	String add() {
		return "modules/job/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		Task job = taskScheduleJobService.get(id);
		model.addAttribute("job", job);
		return "modules/job/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public ResultMap info(@PathVariable("id") Long id) {
		Task taskScheduleJob = taskScheduleJobService.get(id);
		return ResultMap.success().put("taskScheduleJob", taskScheduleJob);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public ResultMap save(Task taskScheduleJob) {
		if (taskScheduleJobService.save(taskScheduleJob) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResultMap update(Task taskScheduleJob) {
		taskScheduleJobService.update(taskScheduleJob);
		return ResultMap.success();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public ResultMap remove(Long id) {
		if (taskScheduleJobService.remove(id) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public ResultMap remove(@RequestParam("ids[]") Long[] ids) {
		taskScheduleJobService.batchRemove(ids);
		return ResultMap.success();
	}

	@PostMapping(value = "/changeJobStatus")
	@ResponseBody
	public ResultMap changeJobStatus(Long id,String cmd ) {
		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		} else {
			label = "停止";
		}
		try {
			taskScheduleJobService.changeStatus(id, cmd);
			return ResultMap.success("任务" + label + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultMap.success("任务" + label + "失败");
	}

}
