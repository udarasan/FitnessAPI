package com.perspicaz.jim.modules.user;

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
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "dob",nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name = "occupation", length = 50,nullable = true)
    private String occupation;

    @Column(name = "sex", length = 50,nullable = true)
    private String sex;

    @Column(name = "weight", length = 50,nullable = true)
    private double weight;

    @Column(name = "height", length = 50,nullable = true)
    private double height;

    @Column(name = "goal", length = 50,nullable = true)
    private String goal;

    @Column(name = "telephone", length = 50,nullable = true)
    private String telephone;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id",nullable = true)
    private UserProfile profile;

    @OneToOne
    @JoinColumn(name = "meal_plan_id", referencedColumnName = "id",nullable = true)
    private MealPlan mealPlan;

    @OneToOne
    @JoinColumn(name = "workout_plan_id", referencedColumnName = "id",nullable = true)
    private WorkOutPlan workOutPlan;

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "id")
    private Trainer trainer;

    @ElementCollection
    private List<UserWorkout> userWorkouts;

    @ElementCollection
    private List<TrainerComment> trainerComments;
}

