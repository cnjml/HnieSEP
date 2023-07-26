package com.hniesep.framework.util;

import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 吉铭炼
 */
@Component
public class UrlUtil {

    /**
     * 判断链接是否有效
     * 输入链接
     * 返回true或者false
     */
    public static boolean isUrlValid(String strLink) {
        URL url;
        try {
            url = new URL(strLink);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("HEAD");
            String strMessage = connect.getResponseMessage();
            if (strMessage.compareTo("{\"error\":\"download token is necessary to access protected bucket\",\"error_code\":\"AccessDenied\"}") == 0) {
                return false;
            }
            connect.disconnect();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
