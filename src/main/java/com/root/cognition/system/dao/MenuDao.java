package com.root.cognition.system.dao;


import com.root.cognition.system.entity.Menu;
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
public interface MenuDao extends BaseDao<Menu> {

    List<Menu> listMenuByUserId(String id);

    List<String> listUserPerms(Long id);
}
