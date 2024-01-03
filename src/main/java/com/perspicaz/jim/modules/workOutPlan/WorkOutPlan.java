package com.perspicaz.jim.modules.workOutPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "work_out_plan")
@Getter
@Setter
@ToString
public class WorkOutPlan  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "workout_plan_name", length = 50)
    private String planName;

    @Column(name = "sex", length = 50)
    private String sex;

    @Column(name = "calories_burn", length = 10)
    private double caloriesBurn;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> exercises;


}