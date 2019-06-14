package com.root.cognition.modules.controller;

import com.root.cognition.common.persistence.BaseController;
import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.modules.entity.Dict;
import com.root.cognition.modules.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//    @RequiresPermissions("common:dict:dict")
    String dict() {
        return "modules/dict/dict";
    }

    @ResponseBody
    @GetMapping("/list")
//    @RequiresPermissions("common:dict:dict")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Dict> dictList = dictService.list(query);
        int total = dictService.count(query);
        PageUtils pageUtils = new PageUtils(dictList, total);
        return pageUtils;
    }

    @GetMapping("/add")
//    @RequiresPermissions("common:dict:add")
    String add() {
        return "modules/dict/add";
    }

    @GetMapping("/edit/{id}")
//    @RequiresPermissions("common:dict:edit")
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
//    @RequiresPermissions("common:dict:add")
    public ResultMap save(Dict dict) {
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
//    @RequiresPermissions("common:dict:edit")
    public ResultMap update(Dict dict) {
        dictService.update(dict);
        return ResultMap.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
//    @RequiresPermissions("common:dict:remove")
    public ResultMap remove(Long id) {
        if (dictService.remove(id) > 0) {
            return ResultMap.success();
        }
        return ResultMap.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
//    @RequiresPermissions("common:dict:batchRemove")
    public ResultMap remove(@RequestParam("ids[]") Long[] ids) {
        dictService.batchRemove(ids);
        return ResultMap.success();
    }

    @GetMapping("/type")
    @ResponseBody
    public List<Dict> listType() {
        return dictService.listType();
    }

    ;

    // 类别已经指定增加
    @GetMapping("/add/{type}/{description}")
//    @RequiresPermissions("common:dict:add")
    String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
        model.addAttribute("type", type);
        model.addAttribute("description", description);
        return "common/dict/add";
    }

    @ResponseBody
    @GetMapping("/list/{type}")
    public List<Dict> listByType(@PathVariable("type") String type) {
        // 查询列表数据
        Map<String, Object> map = Query.withDelFlag();
        map.put("type", type);
        List<Dict> dictList = dictService.list(map);
        return dictList;
    }
}
