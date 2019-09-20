package com.root.cognition.business.controller;

import java.util.List;
import java.util.Map;

import com.root.cognition.business.entity.CarInspect;
import com.root.cognition.business.service.CarInspectService;
import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-19 14:16:54
 */
 
@Controller
@RequestMapping("/business/carInspect")
public class CarInspectController {

	private CarInspectService carInspectService;

	@Autowired
	public void setCarInspectService(CarInspectService carInspectService) {
		this.carInspectService = carInspectService;
	}

	@RequestMapping("")
//	@RequiresPermissions("system:carInspect:carInspect")
	String carInspect(){
	    return "business/carInspect/carInspect";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:carInspect:carInspect")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Map<String,Object> query = new Query(params);
		List<CarInspect> carInspectList = carInspectService.list(query);
		int total = carInspectService.count(query);
		return new PageUtils(carInspectList, total);
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:carInspect:add")
	String add(){
	    return "business/carInspect/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:carInspect:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CarInspect carInspect = carInspectService.get(id);
		model.addAttribute("carInspect", carInspect);
	    return "business/carInspect/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:carInspect:add")
	public ResultMap save(CarInspect carInspect){
		if(carInspectService.save(carInspect)>0){
			return ResultMap.success();
		}
		return ResultMap.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:carInspect:edit")
	public ResultMap update( CarInspect carInspect){
		carInspectService.update(carInspect);
		return ResultMap.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:carInspect:remove")
	public ResultMap remove( Long id){
		if(carInspectService.remove(id)>0){
		return ResultMap.success();
		}
		return ResultMap.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:carInspect:batchRemove")
	public ResultMap remove(@RequestParam("ids[]") Long[] ids){
		carInspectService.batchRemove(ids);
		return ResultMap.success();
	}
	
}
