package com.hniesep.base.filter;


import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * @author 吉铭炼
 * 跨域资源共享
 */

@Component
public class MyCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        设置返回头
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
//        设置状态码
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        设置允许访问的源，无需修改
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//        设置允许的请求头
        response.setHeader("Access-Control-Allow-Headers","token,content-type");
//        设置允许的方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        设置有效时间，单位秒
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

}
