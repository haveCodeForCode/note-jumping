package com.root.cognition.system.config.shiro;


import com.root.cognition.common.config.Constant;
import com.root.cognition.system.config.ApplicationContextRegister;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * @author taoya
 */

public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		Set<String> perms = menuService.menuListPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginInfo = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		UserDao userDao = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		User user = userDao.get(Long.valueOf(loginInfo));

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getUserPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getDelFlag() == Constant.STRING_ZERO) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		return new SimpleAuthenticationInfo(user, password, getName());
	}

}
