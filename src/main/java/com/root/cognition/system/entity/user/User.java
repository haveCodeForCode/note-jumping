package com.root.cognition.system.entity.user;

import com.root.cognition.system.entity.DataEntity;

/**
 * @author LineInkBook
 * @version 2018/12/24
 */
public class User extends DataEntity<User> {

    /**
     * 用户编号
     *
     * userId
     */
    private String userNumber;
    /**
     * 用户名
     *
     * userName
     */
    private String userName;
    /**
     * 用户密码
     *
     * userPassword
     */
    private String userPassword;
    /**
     * 用户邮箱
     *
     * userEmail
     */
    private String userEmail;
    /**
     * 手机号
     *
     * userMobile
     */
    private String userMobile;

    public User(String id) {
        super(id);
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
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

    @Override
    public String toString() {
        return "User{" +
                "userNumber='" + userNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userMobile='" + userMobile + '\'' +
                '}';
    }
}
