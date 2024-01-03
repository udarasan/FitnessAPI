package com.perspicaz.jim.modules.userProfile.dtos;

import com.perspicaz.jim.common.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserProfileRequestDto {

    private String email;

    private String name;

    private String password;

    private UserRole role;

}