package com.example.monolithic.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberGetRequest {

    private Long id;
    private String username;
}
