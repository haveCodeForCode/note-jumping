package com.root.cognition.system.service;


import com.root.cognition.system.entity.Menu;
import com.root.cognition.common.persistence.Tree;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author LineInkBook
 */
@Service
public interface MenuService {
    /**
     * 根据用户获取菜单
     * @param id
     * @return
     */
    Tree<Menu> getSysMenuTree(String id);

    List<Tree<Menu>> listMenuTree(Long id);

    Tree<Menu> getTree();

    Tree<Menu> getTree(Long id);

    List<Menu> list(Menu menu);

    int remove(String id);

    int save(Menu menu);

    int update(Menu menu);

    Menu get(Long id);

    Set<String> listPerms(String userId);
}
