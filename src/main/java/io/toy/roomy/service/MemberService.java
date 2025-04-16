package io.toy.roomy.service;

import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.MemberType;
import io.toy.roomy.dto.MemberSignupRequestDto;
import io.toy.roomy.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member saveMember(MemberSignupRequestDto dto) {
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());
        member.setName(dto.getName());
        member.setRole(MemberType.USER); // 기본값 USER

        return memberRepository.save(member);
    }
}
