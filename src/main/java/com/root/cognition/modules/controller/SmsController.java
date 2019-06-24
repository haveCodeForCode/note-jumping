package com.root.cognition.modules.controller;

import java.util.List;
import java.util.Map;

import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.modules.entity.SmsLog;
import com.root.cognition.modules.service.SmsLogService;
import com.root.cognition.modules.config.AlibabaSms;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短息日志表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-15 15:54:43
 */

@Controller
@RequestMapping("/system/sms")
public class SmsController {
    
    private SmsLogService smsLogService;

    private AlibabaSms alibabaSms;


    @Autowired
    public void setSmsLogService(SmsLogService smsLogService){
        this.smsLogService = smsLogService;
    }


/**************************************************************************/
    @GetMapping("")
    @RequiresPermissions("modules:sms:smsLog")
    String smsLog(){
        return "modules/smsLog/smsLog";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("modules:sms:smsLog")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Map<String,Object> query = new Query(params);
        List<SmsLog> smsLogList = smsLogService.list(query);
        int total = smsLogService.count(query);
        PageUtils pageUtils = new PageUtils(smsLogList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("modules:sms:add")
    String add(){
        return "modules/smsLog/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("modules:sms:edit")
    String edit(@PathVariable("id") Long id,Model model){
        SmsLog smsLog = smsLogService.get(id);
        model.addAttribute("smsLog", smsLog);
        return "modules/smsLog/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("modules:sms:add")
    public ResultMap save(SmsLog smsLog){
        if(smsLogService.save(smsLog)>0){
            return ResultMap.success();
        }
        return ResultMap.error();
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("modules:sms:edit")
    public ResultMap update( SmsLog smsLog){
        smsLogService.update(smsLog);
        return ResultMap.success();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("modules:sms:remove")
    public ResultMap remove(Long id){
        if(smsLogService.remove(id)>0){
            return ResultMap.success();
        }
        return ResultMap.error();
    }

    /**
     * 删除
     */
    @PostMapping( "/batchRemove")
    @ResponseBody
    @RequiresPermissions("modules:sms:batchRemove")
    public ResultMap remove(@RequestParam("ids[]") Long[] ids){
        smsLogService.batchRemove(ids);
        return ResultMap.success();
    }

}

