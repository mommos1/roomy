package io.toy.roomy.service;

import io.toy.roomy.domain.Member;
import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;

public interface MemberService {
    //회원가입
    Member signup(MemberSignupRequest dto);

    //로그인
    Member loginMember(MemberLoginRequest dto);


}
