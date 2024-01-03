package com.perspicaz.jim.modules.user.embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
@Getter
@Setter
@ToString
public class UserWorkout {
    private double weight;

    private double height;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;

    private double   caloriesTake;

    private double caloriesBurn;
 
}