package com.example.monolithic.domain.member.dto;

import com.example.monolithic.global.property.Regex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberPostRequest {

    @NotBlank
    @Pattern(regexp = Regex.USERNAME)
    private String username;

    @NotBlank
    @Pattern(regexp = Regex.PASSWORD)
    private String password;
}
