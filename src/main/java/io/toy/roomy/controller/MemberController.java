package io.toy.roomy.controller;

import io.toy.roomy.domain.Member;
import io.toy.roomy.dto.MemberSignupRequestDto;
import io.toy.roomy.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequestDto dto) {
        memberService.saveMember(dto);
        return ResponseEntity.ok("회원가입 완료");
    }
}