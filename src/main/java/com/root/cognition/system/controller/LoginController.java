package com.root.cognition.system.controller;

import com.root.cognition.common.config.Constant;
import com.root.cognition.system.persistence.BaseController;
import com.root.cognition.system.persistence.Tree;
import com.root.cognition.common.until.RandomValidateCodeUtil;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.common.until.StringUtils;
import com.root.cognition.common.until.encrypt.Md5Utils;
import com.root.cognition.system.entity.Menu;
import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.MenuService;
import com.root.cognition.system.service.UserService;
import com.root.cognition.system.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆控制器
 *
 * @author LineInkBook
 */
@Controller
public class LoginController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    private MenuService menuService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 网站入口
     * 间接转发方式（redirect）实际是两次HTTP请求，服务器端在响应第一次请求的时候，
     * 让浏览器再向另外一个URL发出请求，从而达到转发的目的。
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    String welcome(Model model) {
        return "redirect:/toInterface";
    }

    /*** 网站引荐 */
    @GetMapping("/toGuide")
    String guide() {
        return "guide";
    }

    /*** 前往登陆页面*/
    @RequestMapping(value = "/toLogin")
    String toLogin() {
        return "login_v1";
    }

    /*** 前往注册页面***/
    @RequestMapping(value = "/toRegister")
    String toRegister() {
        return "register";
    }

    /*** 前往首页**/
    @RequestMapping("/toHome")
    String toHome(){
        return "clienthtml/home";
    }


    /*** 首页 */
    @GetMapping("/toInterface")
    String toInterface(Model model) {
        if (getUser() != null) {
            UserVo userVo = userService.getbyUserId(getUserId());
            List<Menu> menuList = userVo.getMenus();
            //删除菜单父级，只展示详细的菜单
            if (menuList !=null && menuList.size()>0) {
                List<Menu> menus = new ArrayList<>(menuList);
                for (Menu menu:menuList){
                    if (menu.getParentId().toString().equals(Constant.STRING_ZERO)){
                        menus.remove(menu);
                    }
                }
                model.addAttribute("menus", menus);
            }
            model.addAttribute("userInfo", userVo.getUserInfo());
        }
        return "interface";
    }


    /**
     * 后台管理页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/index")
    String toindex(Model model) {
        //获取用户菜单
        List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
        UserVo userVo= userService.getbyUserId(getUserId());
        model.addAttribute("userInfo",userVo.getUserInfo());
        model.addAttribute("menus", menus);
        return "index_v1";
    }


//    @RequestMapping(value = "/index")
//    String index(Model model) {
//        List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
//        Map<String,Object> query = Query.withDelFlag();
//        query.put("userId",getUserId());
//        UserInfo userInfo = userInfoService.get(query);

//        model.addAttribute("name", );
//        FileDO fileDO = fileService.get(getUser().getPicId());
//        if (fileDO != null && fileDO.getUrl() != null) {
//            if (fileService.isExist(fileDO.getUrl())) {
//                model.addAttribute("picUrl", fileDO.getUrl());
//            } else {
//                model.addAttribute("picUrl", "/img/photo_s.jpg");
//            }
//        } else {
//            model.addAttribute("picUrl", "/img/photo_s.jpg");
//        }
//        model.addAttribute("menus", menus);
//        model.addAttribute("username", userInfo.getUserName());
//        return "index_v1";
//    }


    /**
     * 登陆接口
     * @param loginInfo
     * @param password
     * @param verify
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    ResultMap login(String loginInfo, String password, String verify, HttpServletRequest request) {
        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return ResultMap.error("请输入验证码");
            }
            if (!random.equals(verify)) {
                return ResultMap.error("请输入正确的验证码");
            }
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return ResultMap.error("验证码校验失败");
        }

        User user = userService.getWihtLogininfo(loginInfo);
        if (user != null) {
            loginInfo = String.valueOf(user.getId());
        } else {
            return ResultMap.error("用户尚未注册~！");
        }

        password = Md5Utils.encrypt(user.getId().toString(), password);
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(loginInfo, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            return ResultMap.success();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return ResultMap.error(e.getMessage());
        }
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
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            //输出验证码图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
    }

    @GetMapping("/fontIcoList")
    String getFontIcoList (){
        return "FontIcoList";
    }
}
