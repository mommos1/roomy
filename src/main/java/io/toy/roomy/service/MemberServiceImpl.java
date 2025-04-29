package io.toy.roomy.service;

import io.toy.roomy.common.exception.DuplicateMemberException;
import io.toy.roomy.common.exception.LoginFailedException;
import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.MemberType;
import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;
import io.toy.roomy.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    @Transactional
    public Member signup(MemberSignupRequest dto) {
        //중복 체크
        duplicateChk(dto);

        Member member = Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .memberType(MemberType.USER)
                .build();

        return memberRepository.save(member);
    }

    /**
     * 회원중복체크
     * @param dto 회원가입 dto
     */
    private void duplicateChk(MemberSignupRequest dto) {
        memberRepository.findByUsername(dto.getUsername())
                .ifPresent(m -> {
                    throw new DuplicateMemberException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 로그인
     * @param dto 로그인 정보
     */
    public Member loginMember(MemberLoginRequest dto) {
        return memberRepository.findByUsername(dto.getUsername())
                .filter(member -> member.getPassword().equals(dto.getPassword()))
                .orElseThrow(() -> new LoginFailedException("잘못된 비밀번호입니다."));
    }
}
