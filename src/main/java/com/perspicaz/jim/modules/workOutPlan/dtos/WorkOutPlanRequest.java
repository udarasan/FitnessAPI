package com.perspicaz.jim.modules.workOutPlan.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;


@Getter
@Setter
@ToString
public class WorkOutPlanRequest {

    private String planName;

    private String sex;

    private List<String> exercises;

    private String caloriesBurn;

}


