package com.lostfinder.backend.post.dto;

import lombok.Builder;

public class PostResDTO {

    @Builder
    public record CreateResult(
            Long postId,
            String title,
            String imageUrl
    ) {}

    @Builder
    public record Detail(
            Long id,
            String title,
            String content,
            String imageUrl,
            String foundLocation,
            String foundTime,
            String writerName,
            String writerPhone
    ) {}
}
