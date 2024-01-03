package com.perspicaz.jim.modules.foodItem;

import com.perspicaz.jim.common.enums.FoodTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "food_item")
@Getter
@Setter
@ToString
public class FoodItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "food_name", length = 50)
    private String foodName;

    @Column(name = "serveSize", length = 50)
    private String serveSize;

    @Column(name = "caloryAmount", length = 50)
    private double caloryAmount;

    @Column(name = "time", length = 50)
    private FoodTime time;

}