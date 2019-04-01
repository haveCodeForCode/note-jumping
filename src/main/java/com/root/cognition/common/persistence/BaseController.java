package com.root.cognition.common.persistence;


import com.root.cognition.common.shiro.ShiroUtils;
import com.root.cognition.system.entity.SysUser;
import org.springframework.stereotype.Controller;

/**
 * 基础控制层
 * @author taoya
 */
@Controller
public class BaseController {
	public SysUser getUser() {
		return ShiroUtils.getUser();
	}

	public String getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUserName();
	}

}
