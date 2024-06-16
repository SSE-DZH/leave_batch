package com.yanzhen.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =
                registry.addInterceptor(new LoginInterceptor());
        //拦截的内容
        registration.addPathPatterns("/**");
        //不拦截的内容
        registration.excludePathPatterns(
                "/loginIn",
                "/**/login.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.json",
                "/**/images/*.*",
                "/**/static/*/*.*"
        );
    }
}
