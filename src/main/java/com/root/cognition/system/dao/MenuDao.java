package com.root.cognition.system.dao;


import com.root.cognition.system.entity.SysMenu;
import com.root.cognition.common.persistence.BaseDao;
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
public interface MenuDao extends BaseDao<SysMenu> {

    List<SysMenu> listMenuByUserId(String id);

    List<String> listUserPerms(String id);
}
