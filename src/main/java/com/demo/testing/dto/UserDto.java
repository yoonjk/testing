package com.demo.testing.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;
    private String gender;
    private String note;
    private boolean married;
    private String birthday;
    private String profession;
}
