package com.notejumping.system.controller;

import com.notejumping.common.until.Query;
import com.notejumping.common.until.ResultMap;
import com.notejumping.system.entity.Role;
import com.notejumping.system.service.RoleService;
import com.notejumping.system.persistence.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色控制层
 * @author taoya
 */
@RequestMapping("/system/role")
@Controller
public class RoleController extends BaseController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequiresPermissions("sys:role:role")
    @GetMapping("")
    String role() {
        return "system/role/role";
    }

    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:role:role")
    List<Role> list() {
        Map<String, Object> map = Query.withDelFlag();
        List<Role> roleList = roleService.findList(map);
        return roleList;
    }

    //	@Log("添加角色")
    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    String add() {
        return "system/role/add";
    }

    //	@Log("编辑角色")
    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        Role role = roleService.get(id);
        model.addAttribute("role", role);
        return "system/role/edit";
    }

    //	@Log("保存角色")
    @RequiresPermissions("sys:role:add")
    @PostMapping("/save")
    @ResponseBody
    ResultMap save(Role role) {
        // 根据权限存入id，更新人
        role.setCreateBy(getUserId());
        if (roleService.save(role) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("更新角色")
    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    @ResponseBody
    ResultMap update(Role role) {
        //roleId转值id
        int state = roleService.update(role);
        if (state > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("删除角色")
    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody
    ResultMap delete(Long id) {
        if (roleService.delete(id) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.error();
        }
    }

    //	@Log("批量删除角色")
    @RequiresPermissions("sys:role:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    ResultMap batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = roleService.batchDelect(ids);
        if (r > 0) {
            return ResultMap.success();
        }
        return ResultMap.error();

    }
}
