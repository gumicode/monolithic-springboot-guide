package com.example.monolithic.domain.member.controller;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberGetResponse;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.dto.MemberPostResponse;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.service.MemberService;
import com.example.monolithic.global.dto.SuccessResponse;
import com.example.monolithic.global.dto.SuccessResponseHelper;
import com.example.monolithic.global.property.Url;
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

	@GetMapping(Url.MEMBER)
	public SuccessResponse<Page<MemberGetResponse>> getMembers(final Pageable pageable, final MemberGetRequest memberGetRequest) {
		Page<Member> pageMember = memberService.page(pageable, memberGetRequest);
		return SuccessResponseHelper.success(pageMember.map(MemberGetResponse::new));
	}

	@GetMapping(Url.MEMBER + "/{memberId}")
	public SuccessResponse<MemberGetResponse> getMember(@PathVariable final Long memberId) {
		Member member = memberService.find(memberId);
		return SuccessResponseHelper.success(new MemberGetResponse(member));
	}

	@PostMapping(Url.MEMBER)
	public SuccessResponse<MemberPostResponse> postMember(@Validated @RequestBody final MemberPostRequest request) {
		Member member = memberService.save(request);
		return SuccessResponseHelper.success(new MemberPostResponse(member));
	}
}
