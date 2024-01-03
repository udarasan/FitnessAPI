package com.perspicaz.jim.modules.workOutPlan;

import com.perspicaz.jim.common.dtos.ResponseGetDto;
import com.perspicaz.jim.modules.user.User;
import com.perspicaz.jim.modules.workOutPlan.dtos.WorkOutPlanRequest;
import com.perspicaz.jim.modules.workOutPlan.dtos.WorkOutPlanUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/workOut")
public class WorkOutPlanController {

    private final WorkOutPlanService workOutPlanService;

    @PostMapping
    public ResponseGetDto<WorkOutPlan> saveWorkOutPlan(@RequestBody WorkOutPlanRequest workOutPlanRequest) {

        try {
            WorkOutPlan savedWorkOutPlan = workOutPlanService.saveWorkoutPlan(workOutPlanRequest);

            //runtime type safety
            ResponseGetDto <WorkOutPlan> responseGetDto = new ResponseGetDto<WorkOutPlan>();
            responseGetDto.setData(savedWorkOutPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("WorkOutPlan Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<WorkOutPlan> responseGetDto = new ResponseGetDto<WorkOutPlan>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("WorkOutPlan not saved");
            return responseGetDto;
        }

    }

    @GetMapping
    public ResponseGetDto<List<WorkOutPlan>> getWorkOutPlans() {

        try {
            List<WorkOutPlan> savedWorkOutPlan = workOutPlanService.getWorkOutPlans();
            ResponseGetDto<List<WorkOutPlan>> responseGetDto = new ResponseGetDto<List<WorkOutPlan>>();
            responseGetDto.setData(savedWorkOutPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("WorkOutPlan Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<List<WorkOutPlan>> responseGetDto = new ResponseGetDto<List<WorkOutPlan>>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("WorkOutPlan not saved");
            return responseGetDto;
        }

    }

    @PatchMapping
    public ResponseGetDto<WorkOutPlan> updateWorkOutPlan(@RequestBody WorkOutPlanUpdateDto updateRequest) {

        try {

            WorkOutPlan savedWorkOutPlan = workOutPlanService.updateWorkOutPlan(updateRequest);
            ResponseGetDto<WorkOutPlan> responseGetDto = new ResponseGetDto<WorkOutPlan>();
            responseGetDto.setData(savedWorkOutPlan);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("WorkOutPlan updated");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<WorkOutPlan> responseGetDto = new ResponseGetDto<WorkOutPlan>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("No WorkOutPlan exists");
            return responseGetDto;
        }

    }

    @DeleteMapping("/{profileId}")
    public ResponseGetDto<String > deletePlan(@PathVariable String profileId)
    {
        try {
            workOutPlanService.deletePlan(profileId);
            ResponseGetDto< String> responseGetDto = new ResponseGetDto<String>();
            responseGetDto.setData("");
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("Deleted successfully");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto< String> responseGetDto = new ResponseGetDto<   String>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage(e.getMessage());
            return responseGetDto;
        }

    }
}