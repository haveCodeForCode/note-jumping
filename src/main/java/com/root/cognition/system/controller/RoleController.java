package com.root.cognition.system.controller;


import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.system.entity.Role;
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

//    @RequiresPermissions("sys:role:role")
    @GetMapping()
    String role() {
        return prefix + "/role";
    }

//    @RequiresPermissions("sys:role:role")
    @GetMapping("/list")
    @ResponseBody()
    List<Role> list() {
        Map<String, Object> map = Query.withDelFlag();
        List<Role> roleList = roleService.findList(map);
        return roleList;
    }

    //	@Log("添加角色")
//    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    //	@Log("编辑角色")
//    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        Role role = roleService.get(id);
        model.addAttribute("role", role);
        return prefix + "/edit";
    }

    //	@Log("保存角色")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/save")
    @ResponseBody()
    ResultMap save(Role role) {
        if (roleService.save(role) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("更新角色")
//    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    @ResponseBody()
    ResultMap update(Role role) {
        int state = roleService.update(role);
        if (roleService.update(role) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("删除角色")
//    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    ResultMap delete(Long id) {
        if (roleService.delete(id) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("批量删除角色")
//    @RequiresPermissions("sys:role:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    ResultMap batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = roleService.batchDelect(ids);
        if (r > 0) {
            ResultMap.success();
        }
        return ResultMap.error();
    }
}
