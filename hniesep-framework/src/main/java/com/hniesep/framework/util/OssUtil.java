package com.hniesep.framework.util;

import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.protocol.FileType;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 吉铭炼
 */
@Component
public class OssUtil {
    /**
     * 上传凭证
     */
    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.bucket}")
    private String bucket;
    public String uploadImg(String filePath, MultipartFile img){
        String originFileName = img.getOriginalFilename();
        assert originFileName != null;
        if(originFileName.endsWith(FileType.IMAGE_PNG)||originFileName.endsWith(FileType.IMAGE_JPG)){
            return upload(filePath,img);
        }
        throw new SystemException(HttpResultEnum.FILE_TYPE_ERROR);
    }
    private String upload(String key, MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "http://ruu2c97om.hn-bkt.clouddn.com/" + key;
    }
}
