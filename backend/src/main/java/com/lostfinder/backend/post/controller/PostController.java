package com.lostfinder.backend.post.controller;

import com.lostfinder.backend.global.auth.CustomUserDetails;
import com.lostfinder.backend.global.common.ApiResponse;
import com.lostfinder.backend.post.dto.PostReqDTO;
import com.lostfinder.backend.post.dto.PostResDTO;
import com.lostfinder.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<PostResDTO.CreateResult> createPost(
            @RequestPart("data") PostReqDTO.CreatePost dto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @AuthenticationPrincipal CustomUserDetails member
    ) {
        Long memberId = member.getId();
        PostResDTO.CreateResult result = postService.createPost(dto, imageFile, memberId);
        return ApiResponse.success(result);
    }

}
