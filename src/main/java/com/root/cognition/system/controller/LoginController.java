package com.root.cognition.system.controller;

import com.root.cognition.system.entity.SysUser;
import com.root.cognition.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆控制器
 *
 * @author LineInkBook
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 间接转发方式（redirect）实际是两次HTTP请求，服务器端在响应第一次请求的时候，
     * 让浏览器再向另外一个URL发出请求，从而达到转发的目的。
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    String welcome(Model model) {
        return "redirect:/toGuide";
    }

    /**
     * 网站引荐
     */
    @GetMapping("/toGuide")
    String guide() {
        return "guide";
    }

    /**
     * 前往登陆页面
     *
     * @return
     */
    @GetMapping("/toLogin")
    String tologin() {
        return "login";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
//        try {
//            //将用户请求参数封装后，直接提交Shiro处理
//            UsernamePasswordToken token = new UsernamePasswordToken(loginSysUser.getUserName(),loginSysUser.getUserPassword());
//            subject.login(token);
//            //Shiro认证通过后会将user信息放到subject内，生成token并返回
//            SysUser user = (SysUser) subject.getPrincipal();
//            String newToken = userService.generateJwtToken(user.getUsername());
//            response.setHeader("x-auth-token", newToken);
//
//            return ResponseEntity.ok().build();
//        } catch (AuthenticationException e) {
//
//        } catch () {
//
//        }
        return null;
    }

    /**
     * 前往注册页面
     *
     * @return
     */
    @GetMapping("/toRegister")
    String register() {
        return "register";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping({"/index"})
    String index(Model model) {
        return "index";
    }


}
