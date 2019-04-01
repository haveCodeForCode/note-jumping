package com.root.cognition.system.service.impl;


import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.BuildTree;
import com.root.cognition.system.dao.DeptDao;
import com.root.cognition.system.entity.SysDept;
import com.root.cognition.system.service.DeptService;
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
    public SysDept get(String deptId) {
        return deptDao.get(deptId);
    }

    @Override
    public List<SysDept> list(Map<String, Object> map) {
        return deptDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return deptDao.count(map);
    }

    @Override
    public int save(SysDept sysDept) {
        return deptDao.insert(sysDept);
    }

    @Override
    public int update(SysDept sysDept) {
        return deptDao.update(sysDept);
    }

    @Override
    public int remove(String deptId) {
        return deptDao.remove(deptId);
    }

    @Override
    public int batchRemove(String[] deptIds) {
        return deptDao.batchRemove(deptIds);
    }

    @Override
    public Tree<SysDept> getTree() {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> sysSysDepts = deptDao.findList(new HashMap<String, Object>(16));
        for (SysDept sysDept : sysSysDepts) {
            Tree<SysDept> tree = new Tree<SysDept>();

            tree.setId(sysDept.getId().toString());

            tree.setParentId(sysDept.getParentId().toString());

            tree.setText(sysDept.getName());

            Map<String, Object> state = new HashMap<>(16);

            state.put("opened", true);

            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> tree = BuildTree.build(trees);
        return tree;
    }

    @Override
    public boolean checkDeptHasUser(String deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = deptDao.getDeptUserNumber(deptId);
        return result == DataDic.INT_ZERO;
    }

    @Override
    public List<String> listChildrenIds(String parentId) {
        List<SysDept> sysDeptDOS = list(null);
        return treeMenuList(sysDeptDOS, parentId);
    }

    List<String> treeMenuList(List<SysDept> menuList, String pid) {
        List<String> childIds = new ArrayList<>();
        for (SysDept sysDept : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (sysDept.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, sysDept.getId());
                childIds.add(sysDept.getId());
            }
        }
        return childIds;
    }

}
