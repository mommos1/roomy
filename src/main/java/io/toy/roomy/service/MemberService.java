package io.toy.roomy.service;

import io.toy.roomy.dto.request.MemberSignupRequest;

public interface MemberService {

    void signup(MemberSignupRequest dto);

    void loginMember(MemberSignupRequest dto);
}
