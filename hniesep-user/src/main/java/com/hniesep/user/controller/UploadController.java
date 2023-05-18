package com.hniesep.user.controller;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 吉铭炼
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private UploadServiceImpl uploadService;
    @Autowired
    public void setUploadService(UploadServiceImpl uploadService){
        this.uploadService = uploadService;
    }
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public ResponseResult<Object> uploadAvatar(MultipartFile img){
        return uploadService.uploadAvatar(img);
    }

}
