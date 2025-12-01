package com.lostfinder.backend.post.domain;

import com.lostfinder.backend.global.common.BaseEntity;
import com.lostfinder.backend.member.domain.Member;
import com.lostfinder.backend.category.domain.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 글 제목
    @Column(nullable = false, length = 100)
    private String title;

    // 글 내용 (어떤 물건인지, 특징 등)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 물건을 발견한 장소
    @Column(nullable = false, length = 100)
    private String foundLocation;

    // 물건을 발견한 시간
    @Column(nullable = false)
    private LocalDateTime foundTime;

    // 글 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
