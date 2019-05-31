package com.root.cognition.system.entity;


import com.root.cognition.common.persistence.BaseEntity;

/**
 * 部门管理
 *
 * @author LineInkBook
 */
public class Dept extends BaseEntity<Dept> {

    private static final long serialVersionUID = 1L;

    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    public Dept(){

    }

    public Dept(Long id) {
        super(id);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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
