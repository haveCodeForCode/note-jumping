package com.root.cognition.system.service;

import com.root.cognition.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户业务层接口
 *
 * @author LineInkBook
 */
@Service
public interface UserService {

  ///***************联查方法 *****************///

    /**
     * 根据id获取User与Role
     *
     * @param id 用户id
     * @return 返回用户
     */
    User getWithRole(String id);

    /**
     * 根据User中唯一值获取user与Role
     * @param user User对象
     * @return  User对象
     */
    User getByEntityWithRole(User user);


 ///***************通用方法 *****************///

    /**
     * 根据id获取User
     *
     * @param id 用户id
     * @return 返回用户
     */
    User get(String id);

    /**
     * 根据User中唯一值获取user
     * @param user User对象
     * @return  User对象
     */
    User getByEntity(User user);


    List<User> list();

    int save(User user);

    int update(User user);

    int remove(String userId);

    int batchremove(Long[] userIds);

    Set<String> listRoles(Long userId);

    /**
     * 存user登录信息，返回token
     * @param username 用户账户
     * @return tokenString token字符
     */
    String generateJwtToken(String username);

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username 用户账号
     * @return User
     */
    User getJwtTokenInfo(String username);
}
