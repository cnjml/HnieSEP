package com.hniesep.base.entity;

/**
 * @author 吉铭炼
 */
public class User {

    private int id;
    private int status;
    private String username;
    private String regUsername;
    private String regPwd;
    private String password;
    private String tel;
    private String email;
    private String sex;

    @Override
    public String toString() {
        return "{"
                + "\"id\":"
                + id
                + ",\"status\":"
                + status
                + ",\"username\":\""
                + username + '\"'
                + ",\"regUsername\":\""
                + regUsername + '\"'
                + ",\"regPwd\":\""
                + regPwd + '\"'
                + ",\"password\":\""
                + password + '\"'
                + ",\"tel\":\""
                + tel + '\"'
                + ",\"email\":\""
                + email + '\"'
                + ",\"sex\":\""
                + sex + '\"'
                + "}";
    }

    public String getRegUsername() {
        return regUsername;
    }

    public void setRegUsername(String regUsername) {
        this.regUsername = regUsername;
    }

    public String getRegPwd() {
        return regPwd;
    }

    public void setRegPwd(String regPwd) {
        this.regPwd = regPwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
