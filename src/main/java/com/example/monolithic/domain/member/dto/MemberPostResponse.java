package com.example.monolithic.domain.member.dto;

import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.global.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPostResponse extends BaseResponse {

	private Long id;
	private String username;

	public MemberPostResponse(final Member member) {
		this.id = member.getId();
		this.username = member.getUsername();
		this.createDatetime = member.getCreateDatetime();
		this.updateDatetime = member.getUpdateDatetime();
	}
}
