package com.perspicaz.jim.common.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
public class ResponseGetAllDto<T> {

    private T data;

    private Integer totalPages;

    private Integer currentPage;

    private Long totalResults;

    private Integer status;



}


