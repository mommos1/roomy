package io.toy.roomy.service;

import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.MemberType;
import io.toy.roomy.dto.MemberSignupRequest;
import io.toy.roomy.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public void saveMember(MemberSignupRequest dto) {
        Member member = new Member();
        member.setUsername(dto.getUsername());

        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });

        member.setPassword(dto.getPassword());
        member.setName(dto.getName());
        member.setMemberType(MemberType.USER);

        memberRepository.save(member);
    }

    /**
     * 로그인
     *
     * @param dto 로그인 정보
     */
    public void loginMember(MemberSignupRequest dto) {
        memberRepository.findByUsername(dto.getUsername())
                .filter(member -> member.getPassword().equals(dto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 비밀번호입니다."));
    }
}
