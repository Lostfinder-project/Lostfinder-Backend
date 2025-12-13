package com.lostfinder.backend.post.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.mapstruct.Named;

@Component
public class ImageUrlResolver {

    @Value("${app.base-url}")
    private String baseUrl;

    @Named("resolve")
    public String resolve(String filename) {
        if (filename == null) return null;
        return baseUrl + "/uploads/" + filename;
    }
}
