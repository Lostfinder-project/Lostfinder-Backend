package com.lostfinder.backend.post.dto;

import com.lostfinder.backend.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PostReqDTO {

    @Builder
    public record CreatePost(
            @Schema(description = "글 제목", example = "지갑을 주웠습니다")
            String title,
            @Schema(description = "글 내용", example = "학생회관 앞에서 발견했습니다.")
            String content,
            @Schema(description = "발견 위치", example = "학생회관 앞")
            String foundLocation,
            @Schema(description = "카테고리 ID", example = "1")
            Long categoryId
    ) {}
}
