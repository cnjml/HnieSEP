package com.hniesep.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (Board)表实体类
 *
 * @author makejava
 * @since 2023-04-21 16:39:34
 */
@TableName("t_board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    /**
     * 板块ID
     */
    @TableId
     private Integer boardId;
    /**
     * 父板块ID
     */
     private Integer boardParentId;
    /**
     * 板块名称
     */
     private String boardName;
    /**
     * 板块描述
     */
     private String boardDescription;
    /**
     * 排序
     */
     private Integer boardSort;
    /**
     * 发帖权限 1:所有人 0:管理员
     */
     private Integer boardPower;

}

