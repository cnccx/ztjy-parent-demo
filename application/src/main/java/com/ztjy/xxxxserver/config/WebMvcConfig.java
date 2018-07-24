package com.ztjy.xxxxserver.config;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ztjy.common.interceptor.AccessTokenVerifyInterceptor;
/**
*
* @author
*/
@Configuration
@Log4j2
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private AccessTokenVerifyInterceptor accessTokenVerifyInterceptor;

    @Autowired
    public WebMvcConfig(AccessTokenVerifyInterceptor accessTokenVerifyInterceptor) {
        this.accessTokenVerifyInterceptor = accessTokenVerifyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenVerifyInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
