package io.toy.roomy.controller;

import io.toy.roomy.dto.MemberSignupRequest;
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
     * @param dto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest dto) {
        memberService.saveMember(dto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberSignupRequest dto) {
        memberService.saveMember(dto);
        return ResponseEntity.ok("회원가입 완료");
    }
}