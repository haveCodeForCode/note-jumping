package com.root.cognition.system.controller;

import com.root.cognition.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登陆控制器
 * @author LineInkBook
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 间接转发方式（redirect）实际是两次HTTP请求，服务器端在响应第一次请求的时候，
     * 让浏览器再向另外一个URL发出请求，从而达到转发的目的。
     * @param model
     * @return
     */
    @GetMapping("/")
    String welcome(Model model){
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
     * @return
     */
    @GetMapping("/toLogin")
    String login() {
        return "login";
    }

    /**
     * 前往注册页面
     * @return
     */
    @GetMapping("/toRegister")
    String register(){return "register";}

    /**
     *
     * @param model
     * @return
     */
    @GetMapping({"/index"})
    String index(Model model) {
       return "index";
    }


}
