package io.toy.roomy.service;

import io.toy.roomy.dto.request.MemberSignupRequest;
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

    @Test
    void signupTest() {
        MemberSignupRequest dto = new MemberSignupRequest();
        dto.setUsername("test");
        dto.setPassword("1234");
        dto.setName("testName");

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.signup(dto));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void loginTest() {
        MemberSignupRequest dto = new MemberSignupRequest();
        dto.setUsername("test");
        dto.setPassword("1234");
        dto.setName("testName");

        memberService.loginMember(dto);
    }
}
