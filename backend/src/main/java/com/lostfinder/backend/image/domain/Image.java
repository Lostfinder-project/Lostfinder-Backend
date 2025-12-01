package com.lostfinder.backend.image.domain;

import com.lostfinder.backend.global.common.BaseEntity;
import com.lostfinder.backend.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "images")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이미지 URL (로컬 경로, S3 URL 등)
    @Column(nullable = false)
    private String url;
    
    //사진이 있는 글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
