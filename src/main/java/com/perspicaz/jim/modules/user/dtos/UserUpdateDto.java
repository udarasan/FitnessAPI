package com.perspicaz.jim.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perspicaz.jim.modules.mealPlan.MealPlan;
import com.perspicaz.jim.modules.trainer.Trainer;
import com.perspicaz.jim.modules.user.embeddable.TrainerComment;
import com.perspicaz.jim.modules.user.embeddable.UserWorkout;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.workOutPlan.WorkOutPlan;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class UserUpdateDto {

    private long id;

    private String dob;

    private String occupation;

    private String sex;

    private String weight;

    private String height;

    private String goal;

    private String telephone;

    private String profile;

    private String mealPlan;

    private String workOutPlan;

    private String trainer;

    private UserWorkout userWorkout;

    private String trainerComment;
}
