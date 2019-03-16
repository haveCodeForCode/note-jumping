package com.root.cognition.system.service;


import com.root.cognition.system.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

	/**
	 * 通过id获取Role
	 * @param id 角色id
	 * @return
	 */
	Role get(String id);

	List<Role> list();

	List<Role> list(String userId);

	int save(Role role);

	int update(Role role);

	int remove(Long id);

	int batchremove(Long[] ids);
}
