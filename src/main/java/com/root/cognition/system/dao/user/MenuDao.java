package com.root.cognition.system.dao.user;


import com.root.cognition.system.dao.CrudDao;
import com.root.cognition.system.entity.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜单管理
 * @author LineInkBook
 */
@Mapper
public interface MenuDao extends CrudDao<MenuDao> {

	List<Menu> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
