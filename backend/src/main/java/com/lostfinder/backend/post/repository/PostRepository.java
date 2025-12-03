package com.lostfinder.backend.post.repository;

import com.lostfinder.backend.global.common.PageResponse;
import com.lostfinder.backend.post.domain.Post;
import com.lostfinder.backend.post.dto.PostResDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
