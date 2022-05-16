package com.example.monolithic.domain.member.controller;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberGetResponse;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.dto.MemberPostResponse;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.service.MemberService;
import com.example.monolithic.global.dto.SuccessResponse;
import com.example.monolithic.global.dto.SuccessResponseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public SuccessResponse<Page<MemberGetResponse>> getMember(final Pageable pageable, final MemberGetRequest memberGetRequest) {
        Page<Member> pageMember = memberService.pageMember(pageable, memberGetRequest);
        return SuccessResponseHelper.success(pageMember.map(MemberGetResponse::new));
    }

    @GetMapping("/members/{memberId}")
    public SuccessResponse<MemberGetResponse> getMember(@PathVariable final Long memberId) {
        Member member = memberService.getMember(memberId);
        return SuccessResponseHelper.success(new MemberGetResponse(member));
    }

    @PostMapping("/members")
    public SuccessResponse<MemberPostResponse> postMember(@Validated @RequestBody final MemberPostRequest request) {
        Member member = memberService.registerMember(request);
        return SuccessResponseHelper.success(new MemberPostResponse(member));
    }
}
