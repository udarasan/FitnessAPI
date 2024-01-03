package com.perspicaz.jim.modules.mealPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perspicaz.jim.common.enums.FoodTime;
import com.perspicaz.jim.modules.foodItem.FoodItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "meal_plan")
@Getter
@Setter
@ToString
public class MealPlan  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "meal_plan_name", length = 50)
    private String planName;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_foods",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<FoodItem> foodItems;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;
}