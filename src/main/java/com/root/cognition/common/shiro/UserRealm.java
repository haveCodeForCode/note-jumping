package com.root.cognition.common.shiro;


import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.until.Query;
import com.root.cognition.system.config.ApplicationContextRegister;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;

/**
 * @author taoya
 */

public class UserRealm extends AuthorizingRealm {

	private UserDao userDao;
	private MenuService menuService;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

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
		String loginName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		UserDao userDao = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		Map<String, Object> map = Query.withDelFlag();
		map.put("loginName", loginName);
		User user = userDao.getByEntity(map);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getUserPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getDelFlag() == DataDic.STRING_ZERO) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
