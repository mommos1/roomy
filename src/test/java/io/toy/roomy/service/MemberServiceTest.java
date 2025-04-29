package io.toy.roomy.service;

import io.toy.roomy.common.exception.DuplicateMemberException;
import io.toy.roomy.domain.Member;
import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.request.MemberSignupRequest;
import io.toy.roomy.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private MemberRepository memberRepository;

    //회원가입 테스트
    @Test
    void signupTest() {
        //Given
        MemberSignupRequest dto = MemberSignupRequest.builder()
                .username("junitTest")
                .password("1234")
                .name("testName")
                .build();
        //When
        Member member = memberService.signup(dto);
        //Then
        assertThat(memberRepository.findByUsername(member.getUsername())).isPresent();
    }

    //회원가입 중복 예외 테스트
    @Test
    void signupDuplicateTest() {
        //Given
        MemberSignupRequest firstDto = MemberSignupRequest.builder()
                .username("junitTest")
                .password("1234")
                .name("testName")
                .build();
        memberService.signup(firstDto);

        MemberSignupRequest duplicateDto = MemberSignupRequest.builder()
                .username("junitTest")
                .password("5678")
                .name("anotherName")
                .build();
        //When
        DuplicateMemberException e = assertThrows(DuplicateMemberException.class,
                () -> memberService.signup(duplicateDto));
        //Then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    //로그인테스트
    @Test
    void loginTest() {
        //Given
        MemberSignupRequest signupDto = MemberSignupRequest.builder()
                .username("junitTest")
                .password("1234")
                .name("testName")
                .build();
        memberService.signup(signupDto);

        MemberLoginRequest loginDto = MemberLoginRequest.builder()
                .username("junitTest")
                .password("1234")
                .build();
        //When
        Member member = memberService.loginMember(loginDto);
        //Then
        assertThat(member.getUsername()).isEqualTo("junitTest");
    }
}
