package io.toy.roomy.service;

import io.toy.roomy.common.exception.DuplicateMemberException;
import io.toy.roomy.common.exception.LoginFailedException;
import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.MemberType;
import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;
import io.toy.roomy.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     * @param dto 회원가입 요청 데이터 (username, password)
     * @return 로그인 성공 시 Member 객체 반환
     * @throws LoginFailedException 비밀번호가 일치하지 않는 경우
     */
    @Transactional
    public Member signup(MemberSignupRequest dto) {
        //중복 체크
        duplicateChk(dto);

        Member member = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
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
     * 회원 로그인
     * @param dto 로그인 요청 데이터 (username, password)
     * @return 로그인 성공 시 Member 객체 반환
     * @throws LoginFailedException 비밀번호가 일치하지 않는 경우
     */
    public Member loginMember(MemberLoginRequest dto) {
//        return memberRepository.findByUsername(dto.getUsername())
//                .filter(member -> member.getPassword().equals(dto.getPassword()))
//                .orElseThrow(() -> new LoginFailedException("잘못된 비밀번호입니다."));

        Member member = memberRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new LoginFailedException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다.");
        }

        return member;
    }
}
