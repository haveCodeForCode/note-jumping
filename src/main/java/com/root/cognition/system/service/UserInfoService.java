package com.root.cognition.system.service;


import com.root.cognition.system.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Worry
 * @version 2019/4/8
 */
@Service
public interface UserInfoService {

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    UserInfo get(Long id);

    /**
     * 根据条件查询菜单
     *
     * @param params
     * @return
     */
    UserInfo get(Map<String, Object> params);

    /**
     * 根据entity更新菜单
     *
     * @param userInfo
     * @return
     */
    int update(UserInfo userInfo);

}
