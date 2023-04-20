package com.hniesep.base.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吉铭炼
 */
@Data
@NoArgsConstructor
public class ArticleVO<Article> implements Serializable {

    List<Article> articles;

    public ArticleVO(List<Article> articles) {
        this.articles = articles;
    }
}
