package com.dmitrybondarev.shop.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RedirectInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String url = request.getRequestURI();

            if (request.getQueryString() != null)
                url += "?" + request.getQueryString();

            response.setHeader("Turbolinks-Location", url);
        }
    }
}
