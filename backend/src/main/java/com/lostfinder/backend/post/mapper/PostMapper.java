package com.lostfinder.backend.post.mapper;

import com.lostfinder.backend.post.domain.Post;
import com.lostfinder.backend.post.dto.PostResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ImageUrlResolver.class)
public interface PostMapper {

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "createdAt", target = "createAt")
    @Mapping(source = "imageUrl", target = "imageUrl", qualifiedByName = "resolve")
    PostResDTO.PostListResDTO toPostAllDTO(Post post);

    @Mapping(source = "member.name",target = "writerName")
    @Mapping(source = "member.phone", target = "writerPhone")
    @Mapping(source = "foundLocation", target = "foundLocation")
    @Mapping(source = "imageUrl", target = "imageUrl", qualifiedByName = "resolve")
    PostResDTO.Detail toDetailDTO(Post post);

    @Mapping(source = "member.name", target = "writerName")
    @Mapping(source = "member.phone", target = "writerPhone")
    PostResDTO.Contact toContact(Post post);

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "imageUrl", target = "imageUrl", qualifiedByName = "resolve")
    PostResDTO.CreateResult toCreateResult(Post post);
}
