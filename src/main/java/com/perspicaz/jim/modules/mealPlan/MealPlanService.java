package com.perspicaz.jim.modules.mealPlan;

import com.perspicaz.jim.modules.foodItem.FoodItem;
import com.perspicaz.jim.modules.foodItem.FoodItemRepository;
import com.perspicaz.jim.modules.mealPlan.dtos.MealPlanRequest;
import com.perspicaz.jim.modules.mealPlan.dtos.MealPlanUpdateRequest;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final FoodItemRepository foodItemRepository;

    public MealPlan saveMealPlan(MealPlanRequest mealPlanRequest) {
        List<FoodItem> foodItemList = new ArrayList<FoodItem>();

        mealPlanRequest.getFoodList().forEach(
                item -> {
                    foodItemList.add(foodItemRepository.findById(item).get());
                }
        );
        MealPlan mealPlan = new MealPlan();
        mealPlan.setPlanName(mealPlanRequest.getName());
        mealPlan.setFoodItems(foodItemList);
        mealPlan.setAddedDate(LocalDate.now());
        return mealPlanRepository.save(mealPlan);
    }

    public List<MealPlan> getMealPlans() {
        return StreamSupport.stream(mealPlanRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public MealPlan updateMealPlan(MealPlanUpdateRequest updateRequest) {
        MealPlan MealPlanOriginal = mealPlanRepository.findById(updateRequest.getId()).get();
        MealPlanOriginal.setPlanName(updateRequest.getName() != null ? updateRequest.getName() : MealPlanOriginal.getPlanName());

        return mealPlanRepository.save(MealPlanOriginal);

    }

    public List<MealPlan> getReport(UserReportRequest reportRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<MealPlan> mealPlanList = (List<MealPlan>) mealPlanRepository.findAll();

        LocalDate startDate =LocalDate.parse(reportRequest.getStart(),formatter);
        LocalDate endDate =LocalDate.parse(reportRequest.getEnd(),formatter);


        List<MealPlan> mealPlanListRange = mealPlanList.stream()
                .filter(workout -> workout.getAddedDate().compareTo(startDate) >= 0 &&
                        workout.getAddedDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());


        return mealPlanListRange;

    }
}
