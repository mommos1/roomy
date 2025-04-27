package io.toy.roomy.service;

import io.toy.roomy.dto.MemberSignupRequest;

public interface MemberService {

    void saveMember(MemberSignupRequest dto);

    void loginMember(MemberSignupRequest dto);
}
