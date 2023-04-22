package com.hniesep.framework.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
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

}
