package com.example.monolithic.domain.member.dto;

import com.example.monolithic.global.property.Regex;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPostRequest {

    @NotBlank
    @Pattern(regexp = Regex.USERNAME, message = "{REGEX_USERNAME}")
    private String username;

    @NotBlank
    @Pattern(regexp = Regex.PASSWORD, message = "{REGEX_PASSWORD}")
    private String password;
}
