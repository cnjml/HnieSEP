package com.hniesep.framework.service.impl;

import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.UploadService;
import com.hniesep.framework.util.OssUtil;
import com.hniesep.framework.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 吉铭炼
 */
@Service
public class UploadServiceImpl implements UploadService {
    private AccountMapper accountMapper;
    private OssUtil ossUtil;
    @Value("${filepath.avatar}")
    private String avatarPath;
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }
    @Autowired
    public void setOssUtil(OssUtil ossUtil){
        this.ossUtil = ossUtil;
    }
    @Override
    public ResponseResult<Object> uploadAvatar(MultipartFile img) {
        String avatarKey = avatarPath + SecurityUtil.getAccountId().toString();
        String url = ossUtil.uploadImg(avatarKey,img);
        Account account = accountMapper.selectById(SecurityUtil.getAccountId());
        account.setAccountAvatar(url);
        accountMapper.updateById(account);
        return new ResponseResult<>(HttpResultEnum.SUCCESS,url);
    }

}
