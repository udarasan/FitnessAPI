package com.perspicaz.jim.modules.userProfile.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LoginRequest {

    private String email;

    private String password;



}