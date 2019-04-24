package com.root.cognition.system.service.impl;

import com.root.cognition.system.dao.UserInfoDao;
import com.root.cognition.system.entity.UserInfo;
import com.root.cognition.system.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Worry
 * @version 2019/4/8
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserInfo get(Long id) {
        return userInfoDao.get(id);
    }

    @Override
    public UserInfo get(Map<String, Object> params) {
        return userInfoDao.getByEntity(params);
    }

    @Override
    public int update(UserInfo userInfo) {
        return 0;
    }
}
