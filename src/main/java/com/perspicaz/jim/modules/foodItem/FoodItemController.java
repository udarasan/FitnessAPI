package com.perspicaz.jim.modules.foodItem;

import com.perspicaz.jim.common.dtos.ResponseGetDto;

import com.perspicaz.jim.modules.foodItem.dtos.FoodItemRequest;
import com.perspicaz.jim.modules.foodItem.dtos.FoodItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/foodItem")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public ResponseGetDto<FoodItem> saveFoodItem(@RequestBody FoodItemRequest foodItemRequest) {

        try {
            FoodItem savedFoodItem = foodItemService.saveFoodItem(foodItemRequest);
            ResponseGetDto<FoodItem> responseGetDto = new ResponseGetDto<FoodItem>();
            responseGetDto.setData(savedFoodItem);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("FoodItem Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<FoodItem> responseGetDto = new ResponseGetDto<FoodItem>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("FoodItem not saved");
            return responseGetDto;
        }

    }

    @GetMapping
    public ResponseGetDto<List<FoodItem>> getFoodItem() {

        try {
            List<FoodItem> savedFoodItem = foodItemService.getFoodItems();
            ResponseGetDto<List<FoodItem>> responseGetDto = new ResponseGetDto<List<FoodItem>>();
            responseGetDto.setData(savedFoodItem);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("FoodItem get success");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto< List<FoodItem>> responseGetDto = new ResponseGetDto< List<FoodItem>>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("FoodItem get failed");
            return responseGetDto;
        }

    }


    @PatchMapping
    public ResponseGetDto<FoodItem> updateFoodItem(@RequestBody FoodItemUpdateRequest updateRequest) {

        try {

            FoodItem savedFoodItem = foodItemService.updateFoodItem(updateRequest);
            ResponseGetDto<FoodItem> responseGetDto = new ResponseGetDto<FoodItem>();
            responseGetDto.setData(savedFoodItem);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("FoodItem updated");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<FoodItem> responseGetDto = new ResponseGetDto<FoodItem>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("No FoodItem exists");
            return responseGetDto;
        }

    }

}