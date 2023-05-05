package com.hniesep.framework.entity;

import com.hniesep.framework.protocol.HttpResultEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 吉铭炼
 */
@Data
public class ResponseResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 958295628567280402L;
    private Integer code;
    private T data;
    private String msg;
    /**
     * 空参构造
     */
    public ResponseResult(){}
    /**
     * 自定义响应结果
     * @param code 响应码
     * @param msg 信息
     */
    public ResponseResult(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**
     * 自定义响应全参构造
     * @param code 响应码
     * @param data 响应数据
     * @param msg 信息
     */
    public ResponseResult(Integer code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 响应原始数据
     * @param data 原始数据
     */
    public ResponseResult(T data) {
        this.code = 200;
        this.data = data;
        this.msg = "操作成功";
    }
    /**
     * 响应枚举类信息
     * @param resultEnum 响应枚举类
     */
    public ResponseResult(HttpResultEnum resultEnum) {
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
    }
    /**
     * 响应原始数据+枚举类信息
     * @param resultEnum 枚举类信息
     * @param data 原始数据
     */
    public ResponseResult(HttpResultEnum resultEnum,T data) {
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
        this.data = data;
    }
    /**
     * @Method: success
     * @Description: 默认执行成功
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(HttpResultEnum.SUCCESS);
    }
    /**
     * @Method: fail
     * @Description:  默认执行成功带消息
     * @Params: [msg]
     * @History:
     */
    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>(200,msg);
    }
    /**
     * @Method: success
     * @Description:  默认执行成功带结果
     * @Params: [data]
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpResultEnum.SUCCESS,data);
    }
    /**
     * @Method: fail
     * @Description:  默认执行失败
     * @History:
     */
    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(HttpResultEnum.FAILED);
    }
    /**
     * @Method: fail
     * @Description:  默认执行失败带消息
     * @Params: [msg]
     * @History:
     */
    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(400,msg);
    }
    /**
     * @Method: fail
     * @Description:  默认执行失败带响应结果类
     * @Params: [msg]
     * @History:
     */
    public static <T> ResponseResult<T> fail(HttpResultEnum resultEnum) {
        return new ResponseResult<>(resultEnum);
    }
    /**
     * 判断操作是否成功
     * @param result 响应结果
     * @return 是否成功
     * @param <T> 泛型
     */
    public static <T> boolean isSuccess(ResponseResult<T> result) {
        return 200 == result.getCode();
    }
    /**
     * 判断操作是否失败
     * @param result 响应结果
     * @return 是否失败
     * @param <T> 泛型
     */
    public static <T> boolean isFail(ResponseResult<T> result) {
        return !isSuccess(result);
    }
}