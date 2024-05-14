package com.example.diet.plan.Model;

import com.example.diet.plan.Enums.WeekDay;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private WeekDay weekDay;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private DietPlan dietPlan;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Meals> mealsList = new ArrayList<>();

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Exercise> exerciseList = new ArrayList<>();

}
