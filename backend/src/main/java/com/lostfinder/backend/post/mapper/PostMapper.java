package com.lostfinder.backend.post.mapper;

import com.lostfinder.backend.post.domain.Post;
import com.lostfinder.backend.post.dto.PostResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "createdAt", target = "createAt")
    PostResDTO.PostListResDTO toPostAllDTO(Post post);

    @Mapping(source = "member.name",target = "writerName")
    @Mapping(source = "member.phone", target = "writerPhone")
    PostResDTO.Detail toDetailDTO(Post post);

    @Mapping(source = "member.name", target = "writerName")
    @Mapping(source = "member.phone", target = "writerPhone")
    PostResDTO.Contact toContact(Post post);
}
