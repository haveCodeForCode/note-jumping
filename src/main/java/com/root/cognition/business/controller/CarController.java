package com.root.cognition.business.controller;

import com.root.cognition.business.entity.Car;
import com.root.cognition.business.service.CarService;
import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.system.persistence.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 车辆表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-19 14:16:54
 */
 
@Controller
@RequestMapping("/business/car")
public class CarController extends BaseController {
	
	private CarService carService;
	
	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@RequestMapping("")
	String car(){
	    return "business/car/car";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Map<String,Object> query = new Query(params);
		//用户相关列表
		query.put("userId", getUserId());
		List<Car> carList = carService.list(query);
		int total = carService.count(query);
		return new PageUtils(carList, total);
	}

	@GetMapping("/add")
	String add(){
	    return "business/car/add";
	}

	/**
	 * 修改
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model){
		Car car = carService.get(id);
		model.addAttribute("car", car);
		return "business/car/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public ResultMap save(Car car) {
		if (carService.save(car,getUserId()) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	/**
	 * 查询车辆是否存在
	 *
	 * @param carNum
	 * @return
	 */
	@ResponseBody
	@PostMapping("/checkexist")
	public ResultMap checkexist(String carNum) {
		Map<String, Object> param = Query.withDelFlag();
		param.put("carNum", carNum);
		if (carService.getByEntity(param) != null) {
			if (carService.checkCarNum(carNum) != null) {
				return ResultMap.customMap(1, "已添加", null);
			} else {
				return ResultMap.customMap(2, "已存在是否关联", null);
			}
		} else {
			return ResultMap.customMap(3, "可以添加", null);
		}
	}

	/**
	 * 添加中间关系
	 * @param carNum
	 * @return
	 */
	@ResponseBody
	@PostMapping("/addexist")
	public ResultMap addexist(String carNum) {
		if (carService.saveCarUser(carNum, getUserId()) > 0) {
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
		if(carService.remove(id) > 0) {
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
