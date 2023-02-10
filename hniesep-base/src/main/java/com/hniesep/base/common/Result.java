package com.hniesep.base.common;



/**
 * @author 吉铭炼
 * result类
 */
public class Result {

    private Integer code;
    private Object data;
    private String token;
    private String msg ;

    public Result(){}

    public Result(Integer code){
        this.code=code;
    }

    public Result(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
