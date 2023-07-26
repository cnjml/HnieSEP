package com.hniesep.framework.protocol;

/**
 * @author 吉铭炼
 */
public class FieldCode {
    public static final Integer ARTICLE_AUDIT_PASS = 1;
    public static final Integer ARTICLE_AUDIT_FAILED = -1;
    public static final Integer ARTICLE_AUDIT_WAIT = 0;
    public static final Integer ARTICLE_RELEASE_PUBLISH = 1;
    public static final Integer ARTICLE_RELEASE_DRAFT = 1;
    public static final Integer ARTICLE_RELEASE_DELETE = -1;
    public static final Integer ACCOUNT_TYPE_ADMIN = 0;
    public static final Integer ACCOUNT_TYPE_USER = 1;
    public static final Integer COMMENT_STATUS_DELETE = -1;
    public static final Integer COMMENT_STATUS_WAIT = 0;
    public static final Integer COMMENT_STATUS_PASS = 1;
    public static final Integer COMMENT_IS_ROOT = 0;
    public static final Integer LIKE_ARTICLE = 0;
    public static final Integer LIKE_COMMENT = 1;
    public static final Integer COMMENT_LIKED = 1;
    public static final Integer COMMENT_UN_LIKED = 0;
    public static final Integer FAVORITE_TYPE_ARTICLE = 0;

}
