package com.anton.sirm;

import com.anton.sirm.resolver.DokterArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private DokterArgumentResolver dokterArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(dokterArgumentResolver);
    }
}
