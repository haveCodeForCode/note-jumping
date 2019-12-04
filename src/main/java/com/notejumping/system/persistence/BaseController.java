package com.notejumping.system.persistence;


import com.notejumping.system.config.shiro.ShiroUtils;
import com.notejumping.system.entity.User;
import org.springframework.stereotype.Controller;

/**
 * 基础控制层
 * @author taoya
 */
@Controller
public class BaseController {

	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getLoginName();
	}

}
