package com.root.cognition.system.controller;

import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.system.entity.SysMenu;
import com.root.cognition.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制
 * @author taoya
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<SysMenu> list(@RequestParam Map<String, Object> params) {
		List<SysMenu> sysMenus = menuService.list(params);
		return sysMenus;
	}

	//	@Log("添加菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") String pId) {
		model.addAttribute("pId", pId);
		if (pId == DataDic.STRING_ZERO) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getMenuName());
		}
		return prefix + "/add";
	}

	//	@Log("编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") String id) {
		SysMenu sysMenu = menuService.get(id);
		String parentId = sysMenu.getParentId();
		model.addAttribute("pId", parentId);
		if (parentId.equals(DataDic.STRING_ZERO)) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(parentId).getMenuName());
		}
		model.addAttribute("menu", sysMenu);
		return prefix+"/edit";
	}

	//	@Log("保存菜单")
	@RequiresPermissions("sys:sysMenu:add")
	@PostMapping("/save")
	@ResponseBody
	ResultMap save(SysMenu sysMenu) {
		if (menuService.save(sysMenu) > 0) {
			return ResultMap.success();
		} else {
			return ResultMap.returnMap(1, "保存失败", null);
		}
	}

	//	@Log("更新菜单")
	@RequiresPermissions("sys:sysMenu:edit")
	@PostMapping("/update")
	@ResponseBody
	ResultMap update(SysMenu sysMenu) {
		if (menuService.update(sysMenu) > 0) {
			return ResultMap.success();
		} else {
			return ResultMap.returnMap(1, "更新失败", null);
		}
	}

	//	@Log("删除菜单")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	ResultMap remove(String id) {
		if (menuService.remove(id) > 0) {
			return ResultMap.success();
		} else {
			return ResultMap.returnMap(1, "删除失败", null);
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<SysMenu> tree() {
		return menuService.getTree();
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<SysMenu> tree(@RequestParam("roleId") String roleId) {
		return menuService.getTree(roleId);
	}
}
