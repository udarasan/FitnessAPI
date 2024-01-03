package com.perspicaz.jim.modules.mealPlan;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MealPlanRepository extends PagingAndSortingRepository<MealPlan, Long> {

}
