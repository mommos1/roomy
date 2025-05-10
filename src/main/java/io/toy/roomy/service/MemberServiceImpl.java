package io.toy.roomy.service;

import io.jsonwebtoken.Claims;
import io.toy.roomy.common.auth.JwtProvider;
import io.toy.roomy.common.exception.DuplicateMemberException;
import io.toy.roomy.common.exception.LoginFailedException;
import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.type.MemberType;
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
    private final JwtProvider jwtProvider;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * 회원가입
     * @param dto 회원가입 요청 데이터 (username, password, name, memberType)
     * @throws LoginFailedException 아이디가 중복되었을 경우
     */
    @Override
    @Transactional
    public void signup(MemberSignupRequest dto) {
        //중복 체크
        duplicateChk(dto);

        Member member = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .memberType(MemberType.USER)
                .build();

        memberRepository.save(member);
    }

    /**
     * 회원중복체크
     * @param dto 중 username 사용
     */
    @Override
    public void duplicateChk(MemberSignupRequest dto) {
        memberRepository.findByUsername(dto.getUsername())
                .ifPresent(_ -> {
                    throw new DuplicateMemberException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 회원 로그인
     *
     * @param dto 로그인 요청 데이터 (username, password)
     * @return 로그인 성공 시 MemberResponse 객체 반환
     * @throws LoginFailedException 사용자가 존재하지 않거나 비밀번호가 일치하지 않는 경우
     */
    @Override
    public String loginMember(MemberLoginRequest dto) {
        Member member = memberRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new LoginFailedException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다.");
        }

        return jwtProvider.generateToken(member.getUsername(), member.getMemberType().name(), member.getName());
    }

    @Override
    public Claims validateToken(String token) {
        return jwtProvider.validateToken(token);
    }

}
