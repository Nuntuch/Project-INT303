package com.moommim.moommim_web.util.filter;

import com.moommim.moommim_web.config.App;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "DefaultWebFilter", urlPatterns = {"/*"})
public class DefaultWebFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("DefaultWebFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        settingRequest(request);
        if (App.IS_DEV_MODE) {
            getRequestLogging(request);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void settingRequest(ServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            this.context.log("DefaultWebFilter : " + ex.getMessage());
        }
    }

    private void getRequestLogging(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            this.context.log("::Request Params::{ " + name + "= " + value + " }");
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                this.context.log("::Cookie::{ " + cookie.getName() + ", " + cookie.getValue() + " }");
            }
        }
    }

}
