package com.root.cognition.system.entity.menu;

import com.root.cognition.system.entity.DataEntity;

public class Menu extends DataEntity<Menu> {

    public Menu(String id) {
        super(id);
    }

    /**
     *  菜单编号
     *  menuNumber
     */
    private Long menuNumber;
    /**
     *  父菜单ID，一级菜单为0
     *  parentId
     */
    private Long parentId;
    /**
     * 菜单名称
     * name
     */
    private String menuName;
    /**
     *  菜单URL
     *  menuUrl
     */
    private String menuUrl;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     * menuPerms
     */
    private String menuPerms;
    /**
     * 类型 0：目录 1：菜单 2：按钮
     *  menuType
     */
    private Integer menuType;
    /**
     * 菜单图标
     * menuIcon
     */
    private String menuIcon;
    /**
     * 排序
     * orderNum
     */
    private Integer orderNum;

    public Long getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(Long menuNumber) {
        this.menuNumber = menuNumber;
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
                "menuNumber=" + menuNumber +
                ", parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuPerms='" + menuPerms + '\'' +
                ", menuType=" + menuType +
                ", menuIcon='" + menuIcon + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}
