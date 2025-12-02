package com.lostfinder.backend.member.service;

import com.lostfinder.backend.global.exception.CustomException;
import com.lostfinder.backend.global.exception.ErrorCode;
import com.lostfinder.backend.member.domain.Member;
import com.lostfinder.backend.member.dto.MemberReqDTO;
import com.lostfinder.backend.member.dto.MemberResDTO;
import com.lostfinder.backend.member.repository.MemberRepository;
import com.lostfinder.backend.global.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResDTO.SignupResult signup(MemberReqDTO.signup req) {

        // 중복 체크
        if (memberRepository.existsByUsername(req.username())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        if (memberRepository.existsByEmail(req.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .name(req.name())
                .phone(req.phone())
                .email(req.email())
                .build();

        Member saved = memberRepository.save(member);

        return MemberResDTO.SignupResult.builder()
                .memberId(saved.getId())
                .username(saved.getUsername())
                .build();
    }

    @Override
    public MemberResDTO.LoginResult login(MemberReqDTO.login req) {

        Member member = memberRepository.findByUsername(req.username())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(req.password(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(member.getId());

        return MemberResDTO.LoginResult.builder()
                .accessToken(token)
                .build();
    }
}
