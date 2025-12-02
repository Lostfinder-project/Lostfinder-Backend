package com.lostfinder.backend.member.controller;

import com.lostfinder.backend.global.common.ApiResponse;
import com.lostfinder.backend.member.dto.MemberReqDTO;
import com.lostfinder.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<?> signup(@RequestBody MemberReqDTO.signup req) {
        return ApiResponse.success(memberService.signup(req), "회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody MemberReqDTO.login req) {
        return ApiResponse.success(memberService.login(req), "로그인 성공");
    }
}
