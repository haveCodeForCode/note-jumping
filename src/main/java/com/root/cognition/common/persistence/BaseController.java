package com.root.cognition.common.persistence;


import com.root.cognition.common.shiro.ShiroUtils;
import com.root.cognition.system.entity.User;
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
