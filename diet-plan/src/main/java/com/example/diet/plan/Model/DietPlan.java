package com.example.diet.plan.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "dietPlan", cascade = CascadeType.ALL)
    private List<Routine> routineList = new ArrayList<>();
}
