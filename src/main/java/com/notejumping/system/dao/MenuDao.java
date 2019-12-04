package com.notejumping.system.dao;


import com.notejumping.system.persistence.BaseDao;
import com.notejumping.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 菜单管理
 *
 * @author LineInkBook
 */
@Mapper
@Repository("MenuDao")
public interface MenuDao extends BaseDao<Menu> {

    /**
     * 根据用户id查询用户下的菜单
     *
     * @param id
     * @return
     */
    List<Menu> listMenuByUserId(Long id);

    /**
     * 根据用户id查询用户下的权限
     *
     * @param id
     * @return
     */
    List<String> listUserPerms(Long id);
}
