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
public class TrainerComment {


    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;

}