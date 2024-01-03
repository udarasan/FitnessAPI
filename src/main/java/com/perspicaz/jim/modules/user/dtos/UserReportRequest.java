package com.perspicaz.jim.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;



@Getter
@Setter
@ToString
public class UserReportRequest {
    private String start;

    private String end;

    private long profileId;


}