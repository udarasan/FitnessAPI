package com.perspicaz.jim.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserReport {
    private String email;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;

    private double   caloriesTake;

    private double caloriesBurn;

}