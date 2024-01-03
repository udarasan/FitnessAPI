package com.perspicaz.jim.modules.mealPlan.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MealPlanUpdateRequest {

    private  long id;

    private String name;

}