package com.perspicaz.jim.modules.trainer.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TrainerRequest {

    private String email;

    private String name;

    private String password;

}


