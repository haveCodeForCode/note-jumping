package com.root.cognition.system.controller;


import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.system.entity.SysDept;
import com.root.cognition.system.entity.SysRole;
import com.root.cognition.system.entity.SysUser;
import com.root.cognition.system.service.RoleService;
import com.root.cognition.system.service.UserService;
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
@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
//	@Autowired
//	DictService dictService;




	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SysUser> sysSysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysSysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
//	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		Query query = new Query(null);
		List<SysRole> sysRoles = roleService.list(query);
		model.addAttribute("roles", sysRoles);
		return prefix + "/add";
	}

	@RequiresPermissions("sys:user:edit")
//	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @Param("id") String id) {
		SysUser sysUserDO = userService.get(id);
		model.addAttribute("user", sysUserDO);
		List<SysRole> sysRoles = roleService.list(id);
		model.addAttribute("roles", sysRoles);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
//	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	ResultMap save(SysUser user) {
//		user.setUserPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	ResultMap update(SysUser user) {
		if (userService.update(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}


	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	ResultMap updatePeronal(SysUser user) {
		if (userService.updatePersonal(user) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}


	@RequiresPermissions("sys:user:remove")
//	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	ResultMap remove(String id) {
		if (userService.remove(id) > 0) {
			return ResultMap.success();
		}
		return ResultMap.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
//	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	ResultMap batchRemove(@RequestParam("ids[]") String[] userIds) {

		int r = userService.batchremove(userIds);
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
	String resetPwd(@RequestParam("id") String userId, Model model) {
		SysUser sysUserDO = new SysUser();
		sysUserDO.setId(userId);
		model.addAttribute("user", sysUserDO);
		return prefix + "/reset_pwd";
	}

//	@Log("提交更改用户密码")
//	@PostMapping("/resetPwd")
//	@ResponseBody
//	R resetPwd(UserVO userVO) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
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
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
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
	public Tree<SysDept> tree() {
		return userService.getTree();
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

//	@GetMapping("/personal")
//	String personal(Model model) {
//		SysUser sysUserDO = userService.get(getUserId());
//		model.addAttribute("user", sysUserDO);
//		model.addAttribute("hobbyList",dictService.getHobbyList(sysUserDO));
//		model.addAttribute("sexList",dictService.getSexList());
//		return prefix + "/personal";
//	}


//	@ResponseBody
//	@PostMapping("/uploadImg")
//	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
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
