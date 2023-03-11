package com.hniesep.base.entity;

import lombok.Data;

/**
 * @author 吉铭炼
 * result类
 */
@Data
public class Result {
    private Integer code;
    private Object data;
    private String token;
    private String msg;
    public Result(){}
    public Result(Integer code){
        this.code=code;
    }
    public Result(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}