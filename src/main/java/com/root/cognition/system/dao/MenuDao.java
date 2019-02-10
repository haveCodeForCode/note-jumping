package com.root.cognition.system.dao;


import com.root.cognition.system.entity.Menu;
import com.root.cognition.system.persistence.CrudDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜单管理
 * @author LineInkBook
 */
@Mapper
public interface MenuDao extends CrudDao<Menu> {

	List<Menu> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
