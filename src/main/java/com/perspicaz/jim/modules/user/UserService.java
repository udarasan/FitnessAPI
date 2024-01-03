package com.perspicaz.jim.modules.user;

import com.perspicaz.jim.common.enums.UserRole;
import com.perspicaz.jim.common.exceptions.AlreadyExists;
import com.perspicaz.jim.modules.foodItem.FoodItem;
import com.perspicaz.jim.modules.mealPlan.MealPlan;
import com.perspicaz.jim.modules.mealPlan.MealPlanRepository;
import com.perspicaz.jim.modules.trainer.Trainer;
import com.perspicaz.jim.modules.trainer.TrainerRepository;
import com.perspicaz.jim.modules.user.dtos.UserReport;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.user.dtos.UserRequest;
import com.perspicaz.jim.modules.user.dtos.UserUpdateDto;
import com.perspicaz.jim.modules.user.embeddable.TrainerComment;
import com.perspicaz.jim.modules.user.embeddable.UserWorkout;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.userProfile.UserProfileRepository;
import com.perspicaz.jim.modules.workOutPlan.WorkOutPlan;
import com.perspicaz.jim.modules.workOutPlan.WorkOutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final MealPlanRepository mealPlanRepository;
    private final WorkOutRepository workOutRepository;
    private final TrainerRepository trainerRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public User saveUser(UserRequest userRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userRequest.getEmail());
        userProfile.setName(userRequest.getName());
        userProfile.setPassword(userRequest.getPassword());
        userProfile.setRole(UserRole.USER);

        UserProfile profile= userProfileRepository.save(userProfile);

        User user = new User();
        user.setProfile(profile);
        return  userRepository.save(user);

    }

    public  List<User> getAllUser()   {
        List<User> useList = new ArrayList<User>();
        userRepository.findAll().forEach(useList::add);
        return useList;
    }

    public User getSingleUser(String id){
        UserProfile profile = userProfileRepository.findById(Long.parseLong(id)).get();

        User userOriginal =  userRepository.findByProfile(profile);
        return userOriginal;
    }

    public  List<User> getAllUserOfTrainer(String id)   {
        UserProfile profileT = userProfileRepository.findById(Long.parseLong(id)).get();

        Trainer trainer = trainerRepository.findByProfile(profileT);
        List<User> useList = new ArrayList<User>();
        userRepository.findAllByTrainer(trainer).forEach(useList::add);
        return useList;
    }

    public   User updateUser(UserUpdateDto updateRequest) {
        UserProfile profile = userProfileRepository.findById(updateRequest.getId()).get();

        User userOriginal =  userRepository.findByProfile(profile);
        userOriginal.setDob(updateRequest.getDob()!=null? LocalDate.parse(updateRequest.getDob(),formatter):userOriginal.getDob());
        userOriginal.setOccupation(updateRequest.getOccupation()!=null?updateRequest.getOccupation():userOriginal.getOccupation());
        userOriginal.setSex(updateRequest.getSex()!=null?updateRequest.getSex():userOriginal.getSex());
        userOriginal.setWeight(updateRequest.getWeight()!=null?Double.parseDouble(updateRequest.getWeight()):userOriginal.getWeight());
        userOriginal.setHeight(updateRequest.getHeight()!=null?Double.parseDouble(updateRequest.getHeight()):userOriginal.getHeight());
        userOriginal.setGoal(updateRequest.getGoal()!=null?updateRequest.getGoal():userOriginal.getGoal());
        userOriginal.setTelephone(updateRequest.getTelephone()!=null?updateRequest.getTelephone():userOriginal.getTelephone());

        if(updateRequest.getUserWorkout()!=null){
            double calorieTake =0.0;
            double calorieBurn = 0.0;

          List<FoodItem> foodItems = userOriginal.getMealPlan().getFoodItems();
            calorieBurn = userOriginal.getWorkOutPlan().getCaloriesBurn();

            for (FoodItem item : foodItems) {
                calorieTake += item.getCaloryAmount();
            }

            UserWorkout userWorkout =new UserWorkout();
            userWorkout.setAddedDate(LocalDate.now());
            userWorkout.setCaloriesBurn(calorieBurn);
            userWorkout.setCaloriesTake(calorieTake);
            userWorkout.setHeight(updateRequest.getUserWorkout().getHeight());
            userWorkout.setWeight(updateRequest.getUserWorkout().getWeight());

            userOriginal.getUserWorkouts().add(userWorkout);

        }

        if(updateRequest.getTrainerComment()!=null){
            TrainerComment trainerComment = new TrainerComment();
            trainerComment.setComment(updateRequest.getTrainerComment());
            trainerComment.setAddedDate(LocalDate.now());

            userOriginal.getTrainerComments().add(trainerComment);
        }

        if(updateRequest.getMealPlan()!=null){
            MealPlan mealPlan = mealPlanRepository.findById(Long.parseLong(updateRequest.getMealPlan())).get();
            userOriginal.setMealPlan(mealPlan);
        }

        if(updateRequest.getTrainer()!=null){
            UserProfile profileT = userProfileRepository.findById(Long.parseLong(updateRequest.getTrainer())).get();

            Trainer trainer = trainerRepository.findByProfile(profileT);
            userOriginal.setTrainer(trainer);
        }

        if(updateRequest.getWorkOutPlan()!=null){
            WorkOutPlan workOutPlan = workOutRepository.findById(Long.parseLong(updateRequest.getWorkOutPlan())).get();
            userOriginal.setWorkOutPlan(workOutPlan);
        }
    return userRepository.save(userOriginal);
    }

    public List<UserReport> getReport(UserReportRequest reportRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        UserProfile profile = userProfileRepository.findById(reportRequest.getProfileId()).get();

        User userOriginal =  userRepository.findByProfile(profile);

        List<UserWorkout> workouts =userOriginal.getUserWorkouts();

        LocalDate startDate =LocalDate.parse(reportRequest.getStart(),formatter);
        LocalDate endDate =LocalDate.parse(reportRequest.getEnd(),formatter);


        List<UserWorkout> workoutsWithinDateRange = workouts.stream()
                .filter(workout -> workout.getAddedDate().compareTo(startDate) >= 0 &&
                        workout.getAddedDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());

        List<UserReport> userReportList = workoutsWithinDateRange.stream().map(
      element->
                    new UserReport(
                            userOriginal.getProfile().getEmail(),
                            userOriginal.getProfile().getName(),
                            element.getAddedDate(),
                            element.getCaloriesTake(),
                            element.getCaloriesBurn()

                    )

        ) .collect(Collectors.toList());
         return userReportList;

    }

}
