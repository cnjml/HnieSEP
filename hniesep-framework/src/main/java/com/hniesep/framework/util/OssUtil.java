package com.hniesep.framework.util;

import com.google.gson.Gson;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.AccountMapper;
import com.hniesep.framework.protocol.FileType;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.impl.AccountServiceImpl;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Value("${oss.cdn}")
    private String cdn;
    private AccountMapper accountMapper;
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    public String uploadImg(String filePath, MultipartFile img) {
        String originFileName = img.getOriginalFilename();
        assert originFileName != null;
        if (originFileName.endsWith(FileType.IMAGE_PNG) || originFileName.endsWith(FileType.IMAGE_JPG)) {
            return upload(filePath, img);
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
                if (accountMapper.selectById(SecurityUtil.getAccountId()).getAccountAvatar()!=null) {
                    this.delete(key);
                }
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                this.refresh(key);
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
        String[] urls = new String[]{"http://ruu2c97om.hn-bkt.clouddn.com/" + key};
        CdnManager c = new CdnManager(Auth.create(accessKey, secretKey));
        try {
            c.refreshUrls(urls);
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return urls[0];
    }

    /**
     * 删除文件
     *
     * @param url 待删除文件url
     */
    public void delete(String url) {
        try {
            //设置华南的服务器
            Configuration cfg = new Configuration(Region.autoRegion());
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(bucket, url.replaceAll(cdn, ""));
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 刷新文件
     *
     * @param url 待刷新url
     * @return result
     * @throws QiniuException 七牛云异常
     */
    public CdnResult.RefreshResult refresh(String url) throws QiniuException {
        String[] urls = {url};
        Auth auth = Auth.create(accessKey, secretKey);
        CdnManager c = new CdnManager(auth);
        return c.refreshUrls(urls);
    }

}
