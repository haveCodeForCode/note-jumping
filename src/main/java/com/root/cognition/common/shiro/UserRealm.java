package com.root.cognition.common.shiro;

import com.root.cognition.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * 通过Shiro校验用户数据
 */
@Service
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private MenuService menuService;

    /**
     * 通过shiro鉴定权限获取数据
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //通过shiro获取用户id
        String userId = ShiroManager.getUserId();
        //获取用户菜单
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 用户验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("username", username);
//        String password = new String((char[]) token.getCredentials());
//
//        UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
//        // 查询用户信息
//        UserDO user = userMapper.list(map).get(0);
//
//        // 账号不存在
//        if (user == null) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//
//        // 密码错误
//        if (!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("账号或密码不正确");
//        }
//
//        // 账号锁定
//        if (user.getStatus() == 0) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
//        return info;
        return null;
    }
}
