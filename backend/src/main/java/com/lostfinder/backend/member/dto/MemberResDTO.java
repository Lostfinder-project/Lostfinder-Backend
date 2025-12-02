package com.lostfinder.backend.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record SignupResult(
            Long memberId,
            String username
    ) {}

    @Builder
    public record LoginResult(
            String accessToken
    ) {}
}
