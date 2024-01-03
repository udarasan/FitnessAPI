package com.perspicaz.jim.modules.mealPlan;

import com.perspicaz.jim.common.dtos.ResponseGetDto;
import com.perspicaz.jim.modules.mealPlan.dtos.MealPlanRequest;
import com.perspicaz.jim.modules.mealPlan.dtos.MealPlanUpdateRequest;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/mealPlan")
public class MealPlanController {

    private final MealPlanService MealPlanService;

    @PostMapping
    public ResponseGetDto<MealPlan> saveMealPlan(@RequestBody MealPlanRequest mealPlanRequest) {

        try {
            MealPlan savedMealPlan = MealPlanService.saveMealPlan(mealPlanRequest);
            ResponseGetDto<MealPlan> responseGetDto = new ResponseGetDto<MealPlan>();
            responseGetDto.setData(savedMealPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("MealPlan Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<MealPlan> responseGetDto = new ResponseGetDto<MealPlan>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("MealPlan not saved");
            return responseGetDto;
        }

    }

    @GetMapping
    public ResponseGetDto<List<MealPlan>> getMealPlan() {

        try {
            List<MealPlan> savedMealPlan = MealPlanService.getMealPlans();
            ResponseGetDto<List<MealPlan>> responseGetDto = new ResponseGetDto<List<MealPlan>>();
            responseGetDto.setData(savedMealPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("MealPlan get success");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<List<MealPlan>> responseGetDto = new ResponseGetDto<List<MealPlan>>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("MealPlan get failed");
            return responseGetDto;
        }

    }


    @PatchMapping
    public ResponseGetDto<MealPlan> updateMealPlan(@RequestBody MealPlanUpdateRequest updateRequest) {

        try {

            MealPlan savedMealPlan = MealPlanService.updateMealPlan(updateRequest);
            ResponseGetDto<MealPlan> responseGetDto = new ResponseGetDto<MealPlan>();
            responseGetDto.setData(savedMealPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("MealPlan updated");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<MealPlan> responseGetDto = new ResponseGetDto<MealPlan>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("No MealPlan exists");
            return responseGetDto;
        }

    }

    @PostMapping("/report")
    public ResponseGetDto<List<MealPlan>> getUserReport(@RequestBody UserReportRequest reportRequest) {
        List<MealPlan> PurchaseList = MealPlanService.getReport(reportRequest);
        ResponseGetDto<List<MealPlan>> responseGetDto = new ResponseGetDto<List<MealPlan>>();
        responseGetDto.setData(PurchaseList);
        responseGetDto.setStatus(200);
        responseGetDto.setMessage("report List");
        return responseGetDto;
    }

}