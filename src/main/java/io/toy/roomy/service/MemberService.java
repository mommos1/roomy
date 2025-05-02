package io.toy.roomy.service;

import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;

public interface MemberService {
    //회원가입
    void signup(MemberSignupRequest dto);
    //아이디 중복체크
    void duplicateChk(MemberSignupRequest dto);
    //로그인
    String loginMember(MemberLoginRequest dto);
}
