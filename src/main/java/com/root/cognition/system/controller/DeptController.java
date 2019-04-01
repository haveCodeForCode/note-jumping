package com.root.cognition.system.controller;


import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.SysDept;
import com.root.cognition.system.service.DeptService;
import org.apache.ibatis.annotations.Param;
import com.root.cognition.common.until.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {

	private String prefix = "system/dept";
	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

//	@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public List<SysDept> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<SysDept> sysSysDeptList = deptService.list(query);
		return sysSysDeptList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String add(@Param("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equals(DataDic.STRING_ZERO)) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", deptService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@Param("deptId") String deptId, Model model) {
		SysDept sysDept = deptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		SysDept parSysDept = deptService.get(sysDept.getParentId());
		model.addAttribute("parentDeptName", parSysDept.getName());
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysDept:add")
	public ResultMap save(SysDept sysDept) {
		if (deptService.save(sysDept) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public ResultMap update(SysDept sysDept) {
		if (deptService.update(sysDept) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public ResultMap remove(String deptId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(deptService.count(map)>0) {
			return ResultMap.returnMap(1, "包含下级部门,不允许修改",null);
		}
		if(deptService.checkDeptHasUser(deptId)) {
			if (deptService.remove(deptId) > 0) {
				return ResultMap.error();
			}
		}else {
			return ResultMap.returnMap(1, "部门包含用户,不允许修改",null);
		}
		return ResultMap.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public ResultMap remove(@RequestParam("ids[]") String[] deptIds) {
		deptService.batchRemove(deptIds);
		return ResultMap.success();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<SysDept> tree() {
		Tree<SysDept> tree = new Tree<SysDept>();
		tree = deptService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
