package com.lostfinder.backend.post.repository;

import com.lostfinder.backend.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCategory_Id(Long categoryId, Pageable pageable);
}
