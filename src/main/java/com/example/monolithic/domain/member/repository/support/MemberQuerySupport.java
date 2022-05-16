package com.example.monolithic.domain.member.repository.support;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.global.repository.QuerySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.lang.NonNull;

public class MemberQuerySupport extends QuerySupport {

    protected BooleanBuilder where(@NonNull final MemberGetRequest memberGetRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.orAllOf(new BooleanExpression[]{

                memberIdEq(memberGetRequest.getId()),
                memberUsernameContains(memberGetRequest.getUsername())
        });
        return booleanBuilder;
    }
}
