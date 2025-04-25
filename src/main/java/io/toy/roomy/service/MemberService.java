package io.toy.roomy.service;

import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.MemberType;
import io.toy.roomy.dto.MemberSignupRequest;
import io.toy.roomy.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param dto
     * @return
     */
    public Member saveMember(MemberSignupRequest dto) {
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());
        member.setName(dto.getName());

        return memberRepository.save(member);
    }

    /**
     * 로그인
     * @param dto
     * @return
     */
    public Member loginMember(MemberSignupRequest dto) {

        return null;
    }
}
