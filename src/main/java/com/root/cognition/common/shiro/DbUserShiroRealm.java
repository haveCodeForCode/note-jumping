package com.root.cognition.common.shiro;

import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * 同过数据库验证用户（产生token步骤）
 * @author Worry
 * @version 2019/3/3
 */
public class DbUserShiroRealm extends AuthorizingRealm {

    private final Logger log = LoggerFactory.getLogger(DbUserShiroRealm.class);

    private static final String encryptSalt = "F12839WhsnnEV$#23b";
    private UserService userService;

    public DbUserShiroRealm(UserService userService) {
        this.userService = userService;
        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthenticationToken 内部代码分别为getusername与getpassword顾可以强制转换为UsernamePasswordToken
        //这里我们存放、获取用户的唯一信息userName
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        user.setUserNumber(userpasswordToken.getUsername());
        user = userService.getByEntity(user);
        if(StringUtils.isEmpty(user)) {
            throw new AuthenticationException("用户名错误或您尚未注册");
        }
        //抛给shiro判断用户是否正确
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), ByteSource.Util.bytes(encryptSalt), "dbRealm");
    }
}
