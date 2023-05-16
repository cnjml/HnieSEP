package com.hniesep.framework.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BoardVO implements Serializable {
    /**
     * 板块ID notnull 主键自增
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long boardId;
    /**
     * 父板块ID 默认0
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long boardParentId;
    /**
     * 板块名称 notnull
     */
    private String boardName;
    /**
     * 板块描述
     */
    private String boardDescription;

}
