package com.lostfinder.backend.post.controller;

import com.lostfinder.backend.global.auth.CustomUserDetails;
import com.lostfinder.backend.global.common.ApiResponse;
import com.lostfinder.backend.global.common.PageResponse;
import com.lostfinder.backend.post.dto.PostReqDTO;
import com.lostfinder.backend.post.dto.PostResDTO;
import com.lostfinder.backend.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "글 작성", description = "글 정보(JSON)와 이미지 파일을 multipart/form-data 형태로 전송합니다.")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<PostResDTO.CreateResult> createPost(
            @Parameter(description = "글 정보 JSON", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostReqDTO.CreatePost.class)))
            @RequestPart("data") PostReqDTO.CreatePost dto,
            @Parameter(description = "첨부 이미지 파일", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "string", format = "binary")))
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @AuthenticationPrincipal CustomUserDetails member
    ) {
        Long memberId = member.getId();
        PostResDTO.CreateResult result = postService.createPost(dto, imageFile, memberId);
        return ApiResponse.success(result);
    }

    @GetMapping
    public PageResponse<PostResDTO.PostListResDTO> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postService.getAllPosts(pageRequest);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResDTO.Detail> getPostDetail(
            @PathVariable long id
    ) {
        PostResDTO.Detail detail = postService.getPostDetails(id).getData();
        return ApiResponse.success(detail);
    }

    @GetMapping("/{id}/contact")
    public ApiResponse<PostResDTO.Contact> getPostContact(
            @PathVariable long id
    ){
        PostResDTO.Contact contact = postService.getPostContact(id).getData();
        return ApiResponse.success(contact);
    }
}
