package com.hniesep.base.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author 吉铭炼
 */
@Data
public class Article {
    private String title;
    private String author;
    private String content;
    private String[] keywords;
    private Date date;
    private Integer[] commentsId;
    private Integer id;
    private Integer liked;
    private Integer uid;
}