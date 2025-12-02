package com.lostfinder.backend.member.dto;

import lombok.Builder;

public class MemberReqDTO {

    @Builder
    public record signup(
            String username,
            String password,
            String name,
            String phone,
            String email
    ) {}

    @Builder
    public record login(
            String username,
            String password
    ){}
}
