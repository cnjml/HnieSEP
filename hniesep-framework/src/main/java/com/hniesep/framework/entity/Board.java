package com.hniesep.framework.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * (Board)表实体类
 *
 * @author makejava
 * @since 2023-04-25 15:50:27
 */
@TableName("t_board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    /**
     * 板块ID notnull 主键自增
     */
    @TableId
    private Long boardId;
    /**
     * 父板块ID 默认0
     */
    private Long boardParentId;
    /**
     * 板块名称 notnull
     */
    private String boardName;
    /**
     * 板块描述
     */
    private String boardDescription;
    /**
     * 排序权重 默认1
     */
    private Integer boardSort;
    /**
     * 发文权限 0:管理员 1:所有人 默认1
     */
    private Integer boardPower;
    /**
     * 板块状态 0:禁用 1:正常 默认1
     */
    private Integer boardStatus;

}
