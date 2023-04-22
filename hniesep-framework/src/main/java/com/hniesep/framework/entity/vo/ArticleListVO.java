package com.hniesep.framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吉铭炼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVO<T> implements Serializable {
    private Long total;
    private T rows;
}
