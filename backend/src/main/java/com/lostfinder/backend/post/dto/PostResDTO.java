package com.lostfinder.backend.post.dto;

import lombok.Builder;

public class PostResDTO {

    @Builder
    public record CreateResult(
            Long postId,
            String title,
            String imageUrl
    ) {}

    public record PostListResDTO(
       Long postId,
       String title,
       String imageUrl,
       String category,
       String createAt
    ){}

    @Builder
    public record Detail(
            Long id,
            String title,
            String content,
            String imageUrl,
            String foundLocation,
            String writerName,
            String writerPhone,
            double lat,
            double lng
    ) {}

    public record Contact(
            String writerName,
            String writerPhone
    ){}
}
