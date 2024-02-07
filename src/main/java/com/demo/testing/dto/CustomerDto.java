package com.demo.testing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CustomerDto {
    private Long id;

    private String nickname;

    private String email;

    private String joinDate;
}
