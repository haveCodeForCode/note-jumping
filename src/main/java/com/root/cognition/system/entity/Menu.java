package com.root.cognition.system.entity;

import com.root.cognition.common.persistence.BaseEntity;

/**
 * 菜单对象
 *
 * @author LineInkBook
 */
public class Menu extends BaseEntity<Menu>{

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     * <p>
     * name
     */
    private String menuName;

    /**
     * 父菜单ID，一级菜单为0
     * <p>
     * parentId
     */
    private Long parentId;

    /**
     * 菜单URL
     * <p>
     * menuUrl
     */
    private String menuUrl;

    /**
     * 类型 bootstrap：目录 1：菜单 2：按钮
     * <p>
     * menuType
     */
    private Integer menuType;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     * <p>
     * menuPerms
     */
    private String menuPerms;

    /**
     * 菜单图标
     * <p>
     * menuIcon
     */
    private String menuIcon;

    /**
     * 排序
     * <p>
     * orderNum
     */
    private Integer orderNum;

    public Menu(String id) {
        super(id);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPerms() {
        return menuPerms;
    }

    public void setMenuPerms(String menuPerms) {
        this.menuPerms = menuPerms;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuType=" + menuType +
                ", menuPerms='" + menuPerms + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", orderNum=" + orderNum +
                ", id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
