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
public class ArticleListVO<T> implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long total;
    private T rows;
}
