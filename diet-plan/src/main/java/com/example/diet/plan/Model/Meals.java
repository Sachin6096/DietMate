package com.example.diet.plan.Model;

import com.example.diet.plan.Enums.MealType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Meals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private MealType mealType;

    private LocalTime time;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Routine routine;

    @OneToMany(mappedBy = "meals", cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();
}
