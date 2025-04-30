package io.toy.roomy.service;

import io.toy.roomy.domain.Member;
import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;
import io.toy.roomy.dto.response.MemberResponse;

public interface MemberService {
    //회원가입
    void signup(MemberSignupRequest dto);
    //아이디 중복체크
    void duplicateChk(MemberSignupRequest dto);
    //로그인
    MemberResponse loginMember(MemberLoginRequest dto);
}
