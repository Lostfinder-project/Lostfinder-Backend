package com.lostfinder.backend.member.service;

import com.lostfinder.backend.member.dto.MemberReqDTO;
import com.lostfinder.backend.member.dto.MemberResDTO;

public interface MemberService {

    MemberResDTO.SignupResult signup(MemberReqDTO.signup req);

    MemberResDTO.LoginResult login(MemberReqDTO.login req);
}
