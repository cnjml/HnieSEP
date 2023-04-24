package com.hniesep.framework.exception;

import com.hniesep.framework.protocol.HttpResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 吉铭炼
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException {

    private String message;

    private Integer code;

    public SystemException(HttpResultEnum httpResultEnum){
        super(httpResultEnum.getMsg());
        this.code = httpResultEnum.getCode();
        this.message = httpResultEnum.getMsg();
    }

}