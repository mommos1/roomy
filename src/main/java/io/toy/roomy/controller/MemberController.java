package io.toy.roomy.controller;

import io.toy.roomy.dto.request.MemberLoginRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.dto.request.MemberSignupRequest;
import io.toy.roomy.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원가입
     * @param dto 회원가입 정보
     * @return 회원가입 결과
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody MemberSignupRequest dto) {
        memberService.signup(dto);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공"));
    }

    /**
     * 로그인
     * @param dto 로그인 정보
     * @return 로그인 결과
     */
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody MemberLoginRequest dto) {
        memberService.loginMember(dto); // 실패하면 예외 발생
        return ResponseEntity.ok(ApiResponse.success("로그인 성공"));
    }
}