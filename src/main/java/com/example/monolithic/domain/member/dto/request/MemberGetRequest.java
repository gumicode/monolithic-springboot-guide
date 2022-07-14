package com.example.monolithic.domain.member.dto.request;

import com.example.monolithic.domain.member.entity.QMember;
import com.example.monolithic.global.dto.request.BaseRequest;
import com.example.monolithic.global.dto.request.ReqeustSupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberGetRequest extends BaseRequest implements ReqeustSupport<QMember> {

	private Long id;
	private String username;

	@Override
	public BooleanExpression[] getWhere(QMember entity) {
		return new BooleanExpression[]{
				eq(entity.id, this.id),
				contains(entity.username, this.username)
		};
	}
}
