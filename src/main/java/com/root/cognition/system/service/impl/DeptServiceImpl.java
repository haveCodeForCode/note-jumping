package com.root.cognition.system.service.impl;


import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.BuildTree;
import com.root.cognition.common.until.Query;
import com.root.cognition.system.dao.DeptDao;
import com.root.cognition.system.entity.Dept;
import com.root.cognition.system.service.DeptService;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 部门管理表
 * @author taoya
 */
@Service
public class DeptServiceImpl implements DeptService {


    private DeptDao deptDao;

    @Autowired
    public void setDeptDao (DeptDao deptDao){
        this.deptDao = deptDao;
    }

    @Override
    public Dept get(Long deptId) {
        return deptDao.get(deptId);
    }

    @Override
    public Dept get(Map<String, Object> map) {
        return deptDao.getByEntity(map);
    }

    @Override
    public List<Dept> findList(Map<String, Object> map) {
        return deptDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return deptDao.count(map);
    }

    @Override
    public int save(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }

    @Override
    public int delete(Long deptId) {
        return deptDao.remove(deptId);
    }

    @Override
    public int batchDelete(Long[] deptIds) {
        return deptDao.batchRemove(deptIds);
    }




    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
        Map<String, Object> query =Query.withDelFlag();
        List<Dept> sysDepts = deptDao.findList(query);
        for (Dept dept : sysDepts) {
            Tree<Dept> tree = new Tree<Dept>();

            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());

            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Dept> tree = BuildTree.build(trees);
        return tree;
    }

    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        Map<String, Object> query = new HashMap<>();
        query.put("id", deptId);
        int result = deptDao.count(query);
        return result == DataDic.INT_ZERO;
    }

    @Override
    public List<Long> listChildrenIds(Long parentId) {
        Map<String, Object> query = new HashMap<>();
        query.put("parentId", parentId);
        List<Dept> deptDOS = findList(query);
        return treeMenuList(deptDOS, parentId);
    }

    @Override
    public String[] listParentDept() {
        return deptDao.listParentDept();
    }

    @Override
    public String[] listAllDept() {
        return deptDao.listAllDept();
    }

    List<Long> treeMenuList(List<Dept> menuList, Long pid) {
        List<Long> childIds = new ArrayList<>();
        for (Dept dept : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (dept.getParentId().equals(pid)) {
                //递归遍历下一级
                treeMenuList(menuList, dept.getId());
                childIds.add(dept.getId());
            }
        }
        return childIds;
    }

}
