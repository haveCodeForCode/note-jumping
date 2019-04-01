package com.root.cognition.system.controller;


import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.system.entity.SysRole;
import com.root.cognition.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制层
 * @author taoya
 */
@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
    private String prefix = "system/role";

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequiresPermissions("sys:role:role")
    @GetMapping()
    String role() {
        return prefix + "/role";
    }

    @RequiresPermissions("sys:role:role")
    @GetMapping("/list")
    @ResponseBody()
    List<SysRole> list() {
        Map<String, Object> map = new HashMap<>();
        return roleService.list(map);
    }

    //	@Log("添加角色")
    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    //	@Log("编辑角色")
    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        SysRole sysRole = roleService.get(id);
        model.addAttribute("role", sysRole);
        return prefix + "/edit";
    }

    //	@Log("保存角色")
    @RequiresPermissions("sys:sysRole:add")
    @PostMapping("/save")
    @ResponseBody()
    ResultMap save(SysRole sysRole) {
        if (roleService.save(sysRole) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("更新角色")
    @RequiresPermissions("sys:sysRole:edit")
    @PostMapping("/update")
    @ResponseBody()
    ResultMap update(SysRole sysRole) {
        int state = roleService.update(sysRole);
        if (roleService.update(sysRole) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("删除角色")
    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    ResultMap delete(String id) {
        if (roleService.remove(id) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("批量删除角色")
    @RequiresPermissions("sys:role:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    ResultMap batchRemove(@RequestParam("ids[]") String[] ids) {
        int r = roleService.batchDelect(ids);
        if (r > 0) {
            ResultMap.success();
        }
        return ResultMap.error();
    }
}
