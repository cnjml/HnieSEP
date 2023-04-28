package com.hniesep.framework.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hniesep.framework.util.SecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Component
public class AutoMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long accountId = null;
        try{
            accountId = SecurityUtil.getAccountId();
        }catch (Exception e){
            e.printStackTrace();
            accountId = -1L;
        }
        if(accountId!=-1L){
            this.setFieldValByName("accountId",accountId,metaObject);
        }
        this.setFieldValByName("commentTime",new Date(),metaObject);
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("articleCreateTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("accountRegisterTime",new Date(),metaObject);
        this.setFieldValByName("articleUpdateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("articleUpdateTime",new Date(),metaObject);
    }
}
