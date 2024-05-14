package com.example.diet.plan.Model;

import com.example.diet.plan.Enums.ExerciseIntensity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Duration duration;

    private ExerciseIntensity exerciseIntensity;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Routine routine;
}
