package com.perspicaz.jim.modules.foodItem;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodItemRepository extends PagingAndSortingRepository<FoodItem, Long> {

}
