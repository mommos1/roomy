package io.toy.roomy.controller;

import io.toy.roomy.domain.Member;
import io.toy.roomy.dto.LoginResponse;
import io.toy.roomy.dto.MemberSignupRequest;
import io.toy.roomy.service.MemberServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원가입
     * @param dto 회원가입 정보
     * @return 회원가입 결과
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest dto) {
        memberService.saveMember(dto);
        return ResponseEntity.ok("회원가입 완료");
    }

    /**
     * 로그인
     * @param dto 로그인 정보
     * @return 로그인 결과
     */
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody MemberSignupRequest dto) {
        memberService.loginMember(dto); // 실패하면 예외 발생
        return ResponseEntity.ok(new LoginResponse("Y", null, null));
    }
}