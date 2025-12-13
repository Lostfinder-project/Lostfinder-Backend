package com.lostfinder.backend.post.service;

import com.lostfinder.backend.category.domain.Category;
import com.lostfinder.backend.category.repository.CategoryRepository;
import com.lostfinder.backend.global.common.ApiResponse;
import com.lostfinder.backend.global.common.PageResponse;
import com.lostfinder.backend.global.exception.CustomException;
import com.lostfinder.backend.global.exception.ErrorCode;
import com.lostfinder.backend.global.util.FileUtil;
import com.lostfinder.backend.member.domain.Member;
import com.lostfinder.backend.member.repository.MemberRepository;
import com.lostfinder.backend.post.domain.Post;
import com.lostfinder.backend.post.dto.PostReqDTO;
import com.lostfinder.backend.post.dto.PostResDTO;
import com.lostfinder.backend.post.mapper.PostMapper;
import com.lostfinder.backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final MemberRepository memberRepository;
    private final PostMapper postMapper;

    @Value("${app.base-url}")
    private String baseUrl;

    public PostResDTO.CreateResult createPost(PostReqDTO.CreatePost dto,
                                              MultipartFile imageFile,
                                              Long memberId) {
        //  로그인 사용자 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        String savedFilename = (imageFile != null && !imageFile.isEmpty())
                ? fileUtil.saveImage(imageFile)
                : null;

        Category category = categoryRepository.findById(dto.categoryId())
                .orElse(null);

        Post post = Post.builder()
                .title(dto.title())
                .content(dto.content())
                .foundLocation(dto.foundLocation())
                .imageUrl(savedFilename)
                .category(category)
                .lat(dto.lat())
                .lng(dto.lng())
                .member(member)
                .build();

        postRepository.save(post);

        return PostResDTO.CreateResult.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .imageUrl(savedFilename != null ? baseUrl + "/uploads/" + savedFilename : null)  // ✔ 앱이 받는 값은 절대 URL
                .build();

    }

    //모두 조회
    public PageResponse<PostResDTO.PostListResDTO> getAllPosts(PageRequest pageRequest){
        Page<Post> posts = postRepository.findAll(pageRequest);
        return PageResponse.of(posts,postMapper::toPostAllDTO);
    }

    //상세 조회
    public ApiResponse<PostResDTO.Detail> getPostDetails(Long postId){
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return ApiResponse.success(postMapper.toDetailDTO(post));
    }

    // 작성자 연락처 조회
    public ApiResponse<PostResDTO.Contact> getPostContact(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return ApiResponse.success(postMapper.toContact(post));
    }
}
