package com.notejumping.system.controller;

import com.notejumping.common.config.Constant;
import com.notejumping.common.until.*;
import com.notejumping.common.until.encrypt.Md5Utils;
import com.notejumping.system.config.redis.RedisService;
import com.notejumping.system.entity.User;
import com.notejumping.system.vo.UserVo;
import com.notejumping.system.persistence.BaseController;
import com.notejumping.modules.service.SmsLogService;
import com.notejumping.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 注册用户控制层
 *
 * @author taoya
 */
@RequestMapping("/register")
@Controller
public class RegistController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*** 用户服务*/
    private UserService userService;
    /*** 短信与短信日志服务*/
    private SmsLogService smsLogService;
    /*** redis缓存*/
    private RedisService redisService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSmsLogService(SmsLogService smsLogService) {
        this.smsLogService = smsLogService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    //@Log("注册用户")
    @PostMapping("/registerUser")
    @ResponseBody
    Map<String, Object> register(UserVo userVo) {
        try {
            //新用户
            User user = new User();
            //验证用户是否已存在
            String loginInfo = null;
            if (StringUtils.isNotEmpty(userVo.getMobile())) {
                loginInfo = userVo.getMobile();
                //存入手机
                user.setUserMobile(userVo.getMobile());
            } else if (StringUtils.isNotEmpty(userVo.getEmail())) {
                loginInfo = userVo.getEmail();
                user.setUserEmail(userVo.getEmail());
            }
            //存在用户
            User userold = userService.getWihtLogininfo(loginInfo);

            if (userold != null) {
                return ResultMap.error("用户已存在");
            } else {
                //插入很用户
                user.preInsert();
                //按时间时分秒存入
                SimpleDateFormat sim = new SimpleDateFormat("HHmmss");
                int hoursSeconds = Integer.parseInt(sim.format(new Date()));
                String defaultUser = Tools.createNumCode("UR", hoursSeconds);
                user.setLoginName(defaultUser);
                //密码加密
                String password = Md5Utils.encrypt(user.getId().toString(), userVo.getPassword());
                user.setUserPassword(password);
                userService.save(user);
                //删除用户随机验证码
                String randomKey = null;
                if (StringUtils.isNotEmpty(userVo.getMobile())) {
                    randomKey = userVo.getMobile() + "random";
                } else if (StringUtils.isNotEmpty(userVo.getEmail())) {
                    randomKey = userVo.getEmail() + "random";
                }
                redisService.redisDel(randomKey);
                return ResultMap.success("注册成功");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResultMap.error("注册失败");
        }

    }

    /**
     * 验证用户信息是否存在
     *
     * @param params
     * @return
     */
    @PostMapping("/checkExistence")
    @ResponseBody
    boolean checkExistence(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false/
        if (!params.isEmpty()) {
            params = Query.withDelFlag(params);
            return !userService.exit(params);
        } else {
            return false;
        }
    }

    /**
     * 验证短信验证码
     *
     * @param mobile
     * @return
     */
    @PostMapping("/checkCode")
    @ResponseBody
    boolean verifyCode(@RequestParam String mobile, @RequestParam(defaultValue = "") String email, @RequestParam String code) {
        String randomkey = null;
        if (StringUtils.isNotEmpty(mobile)) {
            randomkey = mobile + "random";
        } else if (StringUtils.isNotEmpty(email)) {
            randomkey = email + "random";
        }
        String redisGet = redisService.redisGet(randomkey);
        if (redisGet != null && redisGet.equals(code)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 验证随机数
     *
     * @param verify
     * @param request
     * @return
     */
    @PostMapping("/checkVerify")
    @ResponseBody
    boolean checkVerify(String verify, HttpServletRequest request) {
        //从session中获取随机数
        boolean verifyState = true;
        String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
        if (StringUtils.isBlank(verify)) {
            verifyState = false;
        }
        if (!random.equals(verify)) {
            verifyState = false;
        }
        return verifyState;
    }


    /***
     * 发送验证码
     * @param mobile
     * @return
     */
//	@Log("发送短信验证码")
    @PostMapping("/sendMobileCode")
    @ResponseBody
    ResultMap sendMobileCode(@RequestParam String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return ResultMap.error("手机号码缺失");
        }

        String randomNumber = Tools.getRandomNumber(6);

        try {
            //发送的模块
            String moudle = Constant.REGISTER_SMS;
            //签名模板
            String signName = "知域知识网";
            //短信验证模板
            String templateCode = "SMS_170836933";
            //组装数组
            List<String> stringList = new ArrayList<>();
            stringList.add("code");
            stringList.add(randomNumber);
            String[] keyword = new String[stringList.size()];
            stringList.toArray(keyword);

            int times = smsLogService.snedSmsMessage(moudle, mobile, signName, templateCode, keyword, null);
            if (times > 0) {
                String randomkey = mobile + "random";
                String reidsState = redisService.redisSet(randomkey, randomNumber, 0);
                if (StringUtils.isNotEmpty(reidsState)) {
                    return ResultMap.success("发送成功");
                } else {
                    return ResultMap.error("发送失败");
                }
            } else {
                return ResultMap.error("发送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.error("发送失败");
        }
    }


//    public
}
