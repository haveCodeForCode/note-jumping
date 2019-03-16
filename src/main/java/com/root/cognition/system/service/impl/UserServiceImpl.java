package com.root.cognition.system.service.impl;


import com.root.cognition.common.jwt.JwtUtils;
import com.root.cognition.common.persistence.BaseService;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * user数据操作
 *
 * @author LineInkBook
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 注入userDao声明
     */
    private UserDao userDao;

    /**
     *  注入userDao方法
     * @param userDao dao层实体
     */
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User getWithRole(String id) {
        return null;
    }

    @Override
    public User getByEntityWithRole(User user) {
        return null;
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public User getByEntity(User user) {
        return userDao.getByEntity(user);
    }

    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int remove(String userId) {
        return 0;
    }

    @Override
    public int batchremove(Long[] userIds) {
        return 0;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }


    /**
     * 保存user登录信息，返回token
     * @param username 用户名
     */
    @Override
    public String generateJwtToken(String username) {
        String salt = "12345";
        //JwtUtils.generateSalt();
        //将salt保存到数据库或者缓存中
        //redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
        //生成jwt token，设置过期时间为1小时
        return JwtUtils.sign(username, salt, 3600);
    }

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username 用户名
     */
    @Override
    public User getJwtTokenInfo(String username) {
        String salt = "12345";
        //从数据库或者缓存中取出jwt token生成时用的salt
        //salt = redisTemplate.opsForValue().get("token:"+username);
        User user = new User();
        user.setUserName(username);
        user = getByEntity(user);
//      user.setSalt(salt);
        return user;
    }


}
