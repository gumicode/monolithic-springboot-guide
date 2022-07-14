package com.example.monolithic.global.dto.request;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface ReqeustSupport<E> {

	BooleanExpression[] getWhere(final E entity);
}
