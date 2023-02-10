package com.hniesep.base.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * @author 吉铭炼
 */
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

    @Override
    public String toString() {
        return "{"
                + "\"title\":\""
                + title + '\"'
                + ",\"author\":\""
                + author + '\"'
                + ",\"content\":\""
                + content + '\"'
                + ",\"keywords\":"
                + Arrays.toString(keywords)
                + ",\"date\":"
                + date
                + ",\"commentsId\":"
                + Arrays.toString(commentsId)
                + ",\"id\":"
                + id
                + ",\"liked\":"
                + liked
                + ",\"uid\":"
                + uid
                + "}";
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer[] getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer[] commentsId) {
        this.commentsId = commentsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

}
