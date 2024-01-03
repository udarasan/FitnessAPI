package com.perspicaz.jim.modules.workOutPlan;

import com.perspicaz.jim.common.exceptions.AlreadyExists;
import com.perspicaz.jim.modules.foodItem.FoodItem;
import com.perspicaz.jim.modules.foodItem.FoodItemRepository;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.userProfile.UserProfileRepository;
import com.perspicaz.jim.modules.userProfile.dtos.UserProfileRequestDto;
import com.perspicaz.jim.modules.workOutPlan.dtos.WorkOutPlanRequest;
import com.perspicaz.jim.modules.workOutPlan.dtos.WorkOutPlanUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Service
@RequiredArgsConstructor
public class WorkOutPlanService {

    private final WorkOutRepository workOutRepository;


    public WorkOutPlan saveWorkoutPlan(WorkOutPlanRequest workOutPlanRequest)  {

        WorkOutPlan workOutPlan = new WorkOutPlan();

        workOutPlan.setPlanName(workOutPlanRequest.getPlanName());
        workOutPlan.setExercises(workOutPlanRequest.getExercises());
        workOutPlan.setSex(workOutPlanRequest.getSex());
        workOutPlan.setCaloriesBurn(Double.parseDouble(workOutPlanRequest.getCaloriesBurn()));
        return workOutRepository.save(workOutPlan);
    }



    public List<WorkOutPlan> getWorkOutPlans()  {
        return StreamSupport.stream(workOutRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public WorkOutPlan updateWorkOutPlan(WorkOutPlanUpdateDto updateRequest) {
        WorkOutPlan plan =  workOutRepository.findById(updateRequest.getId()).get();

        plan.setSex(updateRequest.getSex()!=null?updateRequest.getSex():plan.getSex());
        plan.setPlanName(updateRequest.getPlanName()!=null?updateRequest.getPlanName():plan.getPlanName());
        plan.setCaloriesBurn(updateRequest.getCaloriesBurn()!=null?Double.parseDouble(updateRequest.getCaloriesBurn()):plan.getCaloriesBurn());
        return workOutRepository.save(plan);
    }

    public  void deletePlan(String number){
        workOutRepository.deleteById(Long.parseLong(number));
    }

}
