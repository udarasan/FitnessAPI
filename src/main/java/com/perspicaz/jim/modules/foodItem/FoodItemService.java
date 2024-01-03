package com.perspicaz.jim.modules.foodItem;

 
import com.perspicaz.jim.modules.foodItem.dtos.FoodItemRequest;
import com.perspicaz.jim.modules.foodItem.dtos.FoodItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

 


@Slf4j
@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository FoodItemRepository;

    public FoodItem saveFoodItem(FoodItemRequest FoodItemRequest)  {
        FoodItem FoodItem = new FoodItem();
        FoodItem.setFoodName(FoodItemRequest.getFoodName());
        FoodItem.setCaloryAmount(FoodItemRequest.getCaloryAmount());
        FoodItem.setServeSize(FoodItemRequest.getServeSize());
        FoodItem.setTime(FoodItemRequest.getTime());

        return FoodItemRepository.save(FoodItem);
    }

    public List<FoodItem> getFoodItems()  {
        return StreamSupport.stream(FoodItemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public FoodItem updateFoodItem(FoodItemUpdateRequest updateRequest) {
        FoodItem FoodItemOriginal =  FoodItemRepository.findById(updateRequest.getId()).get();
        FoodItemOriginal.setFoodName(updateRequest.getFoodName()!=null?updateRequest.getFoodName():FoodItemOriginal.getFoodName());
        FoodItemOriginal.setTime(updateRequest.getTime()!=null?updateRequest.getTime():FoodItemOriginal.getTime());
        FoodItemOriginal.setServeSize(updateRequest.getServeSize()!=null?updateRequest.getServeSize():FoodItemOriginal.getServeSize());
        FoodItemOriginal.setCaloryAmount(updateRequest.getCaloryAmount()!=null?Double.parseDouble(updateRequest.getCaloryAmount()):FoodItemOriginal.getCaloryAmount());
        return FoodItemRepository.save(FoodItemOriginal);

    }

}
