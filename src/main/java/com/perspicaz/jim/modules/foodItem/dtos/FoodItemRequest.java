package com.perspicaz.jim.modules.foodItem.dtos;

import com.perspicaz.jim.common.enums.FoodTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;


@Getter
@Setter
@ToString
public class FoodItemRequest {

    private String foodName;

    private String serveSize;

    private double caloryAmount;

    private FoodTime time;

}