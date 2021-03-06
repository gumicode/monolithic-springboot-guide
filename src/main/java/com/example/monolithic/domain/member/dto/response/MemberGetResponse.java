package com.example.monolithic.domain.member.dto.response;

import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.global.dto.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGetResponse extends BaseResponse {

	private Long id;
	private String username;

	public MemberGetResponse(final Member member) {
		this.id = member.getId();
		this.username = member.getUsername();
		this.createDatetime = member.getCreateDatetime();
		this.updateDatetime = member.getUpdateDatetime();
	}
}
