package com.hniesep.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Data
public class Article {
    private Integer id;
    private String author;
    private Date createTime;
    private Date updateTime;
    private String title;
    private String content;
    private Integer likes;
    private String[] keywords;
    private Integer[] commentsId;
}