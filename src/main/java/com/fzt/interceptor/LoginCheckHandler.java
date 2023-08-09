package com.fzt.interceptor;

import com.alibaba.fastjson.JSON;
import com.fzt.common.BaseContext;
import com.fzt.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("employee")!=null){
            log.info("已登录");
            Long id = (Long) request.getSession().getAttribute("employee");
            BaseContext.setThreadLocal(id);
           return true;
        }
        if(request.getSession().getAttribute("user")!=null){
            log.info("已登录");
            Long id = (Long) request.getSession().getAttribute("user");
            BaseContext.setThreadLocal(id);
            return true;
        }
        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
         response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
         return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
