package com.root.cognition.system.domain;

import java.util.Date;

/**
 * 用户相关信息信息作用域
 *
 * @author Worry
 * @version 2019/3/12
 */
public class UserRoleDo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     * <p>
     * userId
     */
    private String userId;
    /**
     * 用户名
     * <p>
     * userName
     */
    private String userName;
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
     * userMobile
     */
    private String userMobile;
    /**
     * 用户角色名称
     * <p>
     * roleName
     */
    private String roleName;
    /**
     * 用户角色英文名称
     * <p>
     * roleEname
     */
    private String roleEname;
    /**
     * 用户角色标识
     * <p>
     * roleSign
     */
    private String roleSign;
    /**
     * 角色权限(对外）
     * <p>
     * roleType
     */
    private String rolePermissions;
    /**
     * 角色信息备注
     * <p>
     * remark
     */
    private String remark;
    /**
     * 数据范围
     * <p>
     * dataScope
     */
    private String dataScope;

    /**
     * 创建者
     * <p>
     * createBy
     */
    private String createBy;
    /**
     * 更新者
     * <p>
     * updateBy
     */
    private String updateBy;
    /**
     * 创建日期
     * <p>
     * gmtCreate
     */
    private Date createTime;
    /**
     * 更新日期
     * <p>
     * UpdateTime
     */
    private Date updateTime;
    /**
     * 删除标记（bootstrap：正常；1：删除；2：审核）
     * <p>
     * delFlag
     */
    private String delFlag;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleEname() {
        return roleEname;
    }

    public void setRoleEname(String roleEname) {
        this.roleEname = roleEname;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    public String getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(String rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "UserRoleDo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleEname='" + roleEname + '\'' +
                ", roleSign='" + roleSign + '\'' +
                ", rolePermissions='" + rolePermissions + '\'' +
                ", remark='" + remark + '\'' +
                ", dataScope='" + dataScope + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
