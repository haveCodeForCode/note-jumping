package com.root.cognition.system.service;

import com.root.cognition.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务接口
 * @author LineInkBook
 */
@Service
public interface UserService {

    /**
     * 根据id获取User实体
     * @param id
     * @return
     */
   public User get(String id);

   public User getByEntity(String id);

   public List<User> list();

   public int save(User user);

   public int update(User user);

   public int remove(String userId);

   public int batchremove(Long[] userIds);

    Set<String> listRoles(Long userId);
}
