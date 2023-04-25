package com.hniesep.framework.entity;

import com.hniesep.framework.protocol.HttpResultEnum;
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
        code = HttpResultEnum.SUCCESS.getCode();
        msg = HttpResultEnum.SUCCESS.getMsg();
    }
    /**
     * 正常响应成功
     *
     * @param data 响应数据
     */
    public ResponseResult(T data) {
        this.data = data;
    }
    /**
     * 返回相应结果
     * @param resultEnum 相应枚举类
     */
    public ResponseResult(HttpResultEnum resultEnum) {
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
    }
    public ResponseResult(HttpResultEnum resultEnum,T data) {
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
        this.data = data;
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
    /**
     * 设置响应结果
     *
     * @param code 响应码
     * @param msg 信息
     */
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
        return new ResponseResult<>(HttpResultEnum.SUCCESS);
    }
    /**
     * @Method: success
     * @Description:  默认执行成功带结果
     * @Params: [data]
     * @History:
     **/
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpResultEnum.SUCCESS,data);
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
        return new ResponseResult<>(HttpResultEnum.FAILED);
    }
    public static <T> ResponseResult<T> fail(HttpResultEnum resultEnum) {
        return new ResponseResult<>(resultEnum);
    }
    public static <T> boolean isSuccess(ResponseResult<T> result) {
        return 0 == result.getCode();
    }
    public static <T> boolean isFail(ResponseResult<T> result) {
        return !isSuccess(result);
    }
}