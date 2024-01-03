package com.perspicaz.jim.modules.mealPlan.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MealPlanRequest {

    private String name;

    private List<Long> foodList;

}