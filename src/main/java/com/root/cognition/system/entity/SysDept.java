package com.root.cognition.system.entity;


import com.root.cognition.common.persistence.BaseEntity;

/**
 * 部门管理
 *
 * @author LineInkBook
 */
public class SysDept extends BaseEntity<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 上级部门ID，一级部门为0
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    public SysDept(String id) {
        super(id);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
