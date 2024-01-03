package com.perspicaz.jim.modules.workOutPlan.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class WorkOutPlanUpdateDto {

    private  long id;

    private String planName;

    private String sex;

    private String caloriesBurn;

}
