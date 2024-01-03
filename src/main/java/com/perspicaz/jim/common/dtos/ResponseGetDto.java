package com.perspicaz.jim.common.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseGetDto<T> {

    private T data;

    private Integer status;

    private String message;

}


