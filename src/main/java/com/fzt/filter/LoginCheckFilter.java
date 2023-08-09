package com.fzt.filter;


import com.alibaba.fastjson.JSON;
import com.fzt.common.BaseContext;
import com.fzt.entity.Employee;
import com.fzt.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = {"/*"})
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER =new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI=request.getRequestURI();

        String [] url=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        if(request.getSession().getAttribute("employee")!=null){
            log.info("已登录");
            Long id = (Long) request.getSession().getAttribute("employee");
            BaseContext.setThreadLocal(id);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(request.getSession().getAttribute("user")!=null){
            log.info("已登录");
            Long id = (Long) request.getSession().getAttribute("user");
            BaseContext.setThreadLocal(id);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        boolean check = check(url, requestURI);
        if(check){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }

}
