package com.perspicaz.jim.modules.foodItem.dtos;

import com.perspicaz.jim.common.enums.FoodTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class FoodItemUpdateRequest {

    private  long id;

    private String foodName;

    private String serveSize;

    private String caloryAmount;

    private FoodTime time;

}