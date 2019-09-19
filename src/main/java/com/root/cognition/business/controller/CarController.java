package com.root.cognition.business.controller;

import java.util.List;
import java.util.Map;

import com.root.cognition.business.entity.Car;
import com.root.cognition.business.service.CarService;
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
 * 车辆表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-19 14:16:54
 */
 
@Controller
@RequestMapping("/business/car")
public class CarController {
	
	private CarService carService;
	
	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@RequestMapping("")
//	@RequiresPermissions("system:car:car")
	String car(){
	    return "business/car/car";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:car:car")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Map<String,Object> query = new Query(params);
		List<Car> carList = carService.list(query);
		int total = carService.count(query);
		return new PageUtils(carList, total);
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:car:add")
	String add(){
	    return "business/car/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:car:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Car car = carService.get(id);
		model.addAttribute("car", car);
	    return "business/car/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:car:add")
	public ResultMap save(Car car){
		if(carService.save(car)>0){
			return ResultMap.success();
		}
		return ResultMap.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:car:edit")
	public ResultMap update( Car car){
		carService.update(car);
		return ResultMap.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:car:remove")
	public ResultMap remove( Long id){
		if(carService.remove(id)>0){
		return ResultMap.success();
		}
		return ResultMap.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:car:batchRemove")
	public ResultMap remove(@RequestParam("ids[]") Long[] ids){
		carService.batchRemove(ids);
		return ResultMap.success();
	}
	
}
