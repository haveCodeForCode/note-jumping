package com.root.cognition.system.service.impl;


import com.root.cognition.system.dao.UserDao;

import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public User getByEntity(String id) {
        return null;
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
}
