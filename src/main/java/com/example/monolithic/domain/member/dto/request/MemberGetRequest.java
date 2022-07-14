package com.example.monolithic.domain.member.dto.request;

import com.example.monolithic.domain.member.entity.QMember;
import com.example.monolithic.global.dto.request.QueryDslExpression;
import com.example.monolithic.global.dto.request.QueryDslWhere;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberGetRequest extends QueryDslExpression implements QueryDslWhere<QMember> {

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
