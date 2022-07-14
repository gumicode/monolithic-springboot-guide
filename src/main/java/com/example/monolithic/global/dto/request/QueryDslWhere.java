package com.example.monolithic.global.dto.request;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface QueryDslWhere<E> {

	BooleanExpression[] getWhere(final E entity);
}
