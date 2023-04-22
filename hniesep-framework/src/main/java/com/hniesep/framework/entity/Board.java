package com.hniesep.framework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (Board)表实体类
 *
 * @author 吉铭炼
 * @since 2023-04-22 12:09:54
 */
@TableName("t_board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    /**
     * 板块ID
     */
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
     * 排序权重
     */
     private Integer boardSort;
    /**
     * 发文权限 0:管理员 1:所有人
     */
     private Integer boardPower;
    /**
     * 板块状态 0:禁用 1:正常
     */
     private Integer boardStatus;

}

