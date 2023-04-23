package com.hniesep.framework.entity;

import com.hniesep.framework.protocol.StatusCode;
import com.hniesep.framework.protocol.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author 吉铭炼
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 958295628567280402L;
    /**
     * 结果编码
     */
    private Integer code;
    private T data;
    /**
     * 错误提示
     */
    private String msg;
    /**
     * 默认响应成功
     */
    public ResponseResult() {
        code = StatusCode.SUCCESS;
    }
    /**
     * 正常响应成功
     *
     * @param data 响应数据
     */
    public ResponseResult(T data) {
        this(StatusCode.SUCCESS, data, StatusMessage.SUCCESS);
    }
    /**
     * 设置响应结果
     *
     * @param code 响应码
     * @param data 响应数据
     */
    public ResponseResult(int code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**
     * @Method: success
     * @Description: 默认执行成功
     * @Params: []
     * @History:
     **/
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>();
    }

    /**
     * @Method: success
     * @Description:  默认执行成功带结果
     * @Params: [data]
     * @History:
     **/
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(data);
    }
    /**
     * @Method: fail
     * @Description:  默认执行失败
     * @Params: []
     * @History:
     **/
    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>();
    }
    /**
     * @Method: fail
     * @Description:  默认执行失败带消息
     * @Params: [msg]
     * @History:
     **/
    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(400, msg);
    }
    public static <T> boolean isSuccess(ResponseResult<T> result) {
        return 0 == result.getCode();
    }
    public static <T> boolean isFail(ResponseResult<T> result) {
        return !isSuccess(result);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}