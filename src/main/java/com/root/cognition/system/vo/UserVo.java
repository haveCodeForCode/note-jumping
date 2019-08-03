package com.root.cognition.system.vo;

import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户临时变量
 *
 * @author Worry
 * @version 2019/5/25
 */
public class UserVo implements Serializable {

    /**
     * 用户账户信息
     */
    private User user;
    /**
     * 用户基本信息
     * <p>
     * userInfo
     */
    private UserInfo userInfo;

    /**
     * 部门信息
     * <p>
     * dept
     */
    private Dept dept;

    /**
     * 用户角色对象
     * <p>
     * roleIds
     */
    private List<Role> roles;

    /**
     * 菜单树
     */
    private  List<Tree<Menu>> menus;

    /**
     * 用户相关登陆信息
     */
    private String mobile;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tree<Menu>> getMenus() {
        return menus;
    }

    public void setMenus(List<Tree<Menu>> menus) {
        this.menus = menus;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user=" + user +
                ", userInfo=" + userInfo +
                ", dept=" + dept +
                ", roles=" + roles +
                ", menus=" + menus +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
