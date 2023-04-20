package com.hniesep.base.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 吉铭炼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "统一请求的返回对象")
public class ResponseVO<T> implements Serializable {
    @ApiModelProperty(value = "状态代码")
    private Integer code;
    @ApiModelProperty(value = "消息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResponseVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
