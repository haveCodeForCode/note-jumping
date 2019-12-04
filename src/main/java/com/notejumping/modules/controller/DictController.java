package com.notejumping.modules.controller;

import com.notejumping.common.config.Constant;
import com.notejumping.common.until.PageUtils;
import com.notejumping.common.until.Query;
import com.notejumping.common.until.ResultMap;
import com.notejumping.common.until.StringUtils;
import com.notejumping.system.persistence.BaseController;
import com.notejumping.modules.entity.Dict;
import com.notejumping.modules.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Worry
 * @version 2019/6/10
 */
@Controller
@RequestMapping("/modules/dict")
public class DictController extends BaseController {

    private DictService dictService;

    @Autowired
    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping("")
    @RequiresPermissions("modules:dict:dict")
    String dict() {
        return "modules/dict/dict";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("modules:dict:dict")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Dict> dictList = dictService.list(query);
        int total = dictService.count(query);
        PageUtils pageUtils = new PageUtils(dictList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/treelist")
    @RequiresPermissions("modules:dict:dict")
    public List<Dict> Treelist(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Map<String,Object> query = Query.withDelFlag(params);
        List<Dict> dictList = dictService.list(query);
        return  dictList;
    }



    @GetMapping("/add/{parentId}")
    @RequiresPermissions("modules:dict:add")
    String add(Model model,@PathVariable("parentId")String parentId) {
        if (StringUtils.isEmpty(parentId)){
            parentId = Constant.STRING_ZERO;
        }
        model.addAttribute("parentId",parentId);
        return "modules/dict/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("modules:dict:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        Dict dict = dictService.get(id);
        model.addAttribute("dict", dict);
        return "modules/dict/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("modules:dict:add")
    public ResultMap save(Dict dict) {
        //补充实体内信息
        dict.setCreateBy(getUserId());
        if (dictService.save(dict) > 0) {
            return ResultMap.success();
        }
        return ResultMap.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("modules:dict:edit")
    public ResultMap update(Dict dict) {
        dict.setUpdateBy(getUserId());
        dict.setUpdateTime(new Date());
        dictService.update(dict);
        return ResultMap.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("modules:dict:remove")
    public ResultMap remove(Long id) {
        if (dictService.remove(id) > 0) {
            return ResultMap.success();
        }
        return ResultMap.error();
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("modules:dict:batchRemove")
    public ResultMap remove(@RequestParam("ids[]") Long[] ids) {
        dictService.batchRemove(ids);
        return ResultMap.success();
    }

    @GetMapping("/type")
    @ResponseBody
    public List<Dict> listType() {
        return dictService.listByType(null);
    }

    /**
     * 类别已经指定增加
     */
    @GetMapping("/add/{id}/{type}/{description}")
    @RequiresPermissions("modules:dict:add")
    String addD(Model model,@PathVariable("id") String id, @PathVariable("type") String type, @PathVariable("description") String description) {
        model.addAttribute("parentId",id);
        model.addAttribute("type", type);
        model.addAttribute("description", description);
        return "modules/dict/add";
    }

    @ResponseBody
    @GetMapping("/list/{type}")
    public List<Dict> listByType(@PathVariable("type") String type) {
        // 查询列表数据
        Map<String, Object> map = Query.withDelFlag();
        map.put("type", type);
        return dictService.list(map);
    }
}
