package com.notejumping.system.controller;


import com.notejumping.common.until.PageUtils;
import com.notejumping.common.until.Query;
import com.notejumping.common.until.ResultMap;
import com.notejumping.system.entity.Dept;
import com.notejumping.system.entity.Role;
import com.notejumping.system.entity.User;
import com.notejumping.system.service.RoleService;
import com.notejumping.system.persistence.BaseController;
import com.notejumping.system.persistence.Tree;
import com.notejumping.modules.service.DictService;
import com.notejumping.system.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author taoya
 */
@RequestMapping("/system/user")
@Controller
public class UserController extends BaseController {


	private UserService userService;
	private RoleService roleService;
	private DictService dictService;

	@Autowired
	public void setUserService(UserService userService){this.userService = userService;}

	@Autowired
	public void setRoleService(RoleService roleService){this.roleService = roleService;}

	@Autowired
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return "system/user/user";
	}

	@GetMapping("/list")
	@ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<User> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
//	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<Role> roles = roleService.list(getUser().getId());
		model.addAttribute("roles", roles);
		return "system/user/add";
	}

	@RequiresPermissions("sys:user:edit")
//	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @Param("id") Long id) {
		User user = userService.get(id);
		model.addAttribute("user", user);
		List<Role> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return "system/user/edit";
	}

	@RequiresPermissions("sys:user:add")
//	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
    ResultMap save(User user) {
//		user.setUserPassword(Md5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	ResultMap update(User user) {
		if (userService.update(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}


	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	ResultMap updatePeronal(User user) {
		if (userService.updatePersonal(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}


	@RequiresPermissions("sys:user:remove")
//	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	ResultMap remove(Long id) {
		if (userService.delete(id) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
//	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	ResultMap batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchDelete(userIds);
		if (r > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions("sys:user:resetPwd")
//	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@RequestParam("id") Long userId, Model model) {
		User user = new User();
		user.setId(userId);
		model.addAttribute("user", user);
		return "system/user/reset_pwd";
	}

//	@Log("提交更改用户密码")
//	@PostMapping("/resetPwd")
//	@ResponseBody
//	R resetPwd(UserVO userVO) {
//		try{
//			userService.resetPwd(userVO,getUser());
//			return R.ok();
//		}catch (Exception e){
//			return R.error(1,e.getMessage());
//		}
//
//	}
//	@RequiresPermissions("sys:user:resetPwd")
//	@Log("admin提交更改用户密码")
//	@PostMapping("/adminResetPwd")
//	@ResponseBody
//	R adminResetPwd(UserVO userVO) {
//		try{
//			userService.adminResetPwd(userVO);
//			return R.ok();
//		}catch (Exception e){
//			return R.error(1,e.getMessage());
//		}
//
//	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<Dept> tree() {
		return userService.getTree();
	}

	@GetMapping("/treeView")
	String treeView() {
		return "system/user/userTree";
	}


//	@ResponseBody
//	@PostMapping("/uploadImg")
//	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
//		Map<String, Object> result = new HashMap<>();
//		try {
//			result = userService.updatePersonalImg(file, avatar_data, getUserId());
//		} catch (Exception e) {
//			return R.error("更新图像失败！");
//		}
//		if(result!=null && result.size()>0){
//			return R.ok(result);
//		}else {
//			return R.error("更新图像失败！");
//		}
//	}
}
