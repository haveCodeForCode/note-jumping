package com.root.cognition.system.entity;

import com.root.cognition.common.persistence.BaseEntity;

import java.util.List;

/**
 * 用户Entity
 * @author LineInkBook
 * @version 2018/12/24
 */
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆账号名
     * <p>
     * userName
     */
    private String loginName;

    /**
     * 用户密码
     * <p>
     * userPassword
     */
    private String userPassword;

    /**
     * 用户邮箱
     * <p>
     * userEmail
     */
    private String userEmail;

    /**
     * 手机号
     * <p>
     */
    private String userMobile;
//**************零时变量************************
    /**
     * 用户信息
     * <p>
     * userInfo
     */
    private Long userInfoId;

    /**
     * 部门
     * <p>
     * dept
     */
    private Long deptId;

    /**
     * 用户角色对象
     * <p>
     * roleIds
     */
    private List<Long> roleIds;

    public User(long id) {
        super(id);
    }

    public User() {

    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getDeptId() {
        return deptId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userInfoId='" + userInfoId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", roleIds=" + roleIds +
                ", id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
