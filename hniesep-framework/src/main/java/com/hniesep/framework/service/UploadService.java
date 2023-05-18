package com.hniesep.framework.service;

import com.hniesep.framework.entity.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HKRR
 */
@Service
public interface UploadService {
    /**
     * 上传图片
     * @param img 图片
     * @return 响应
     */
    ResponseResult<Object> uploadAvatar(MultipartFile img);
}
