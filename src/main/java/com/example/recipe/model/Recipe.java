package com.example.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "recipes")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String cuisine;

    private Double rating;

    @JsonProperty("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    private Integer cookTime;

    @JsonProperty("total_time")
    private Integer totalTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String serves;

    @Embedded
    private Nutrients nutrients;

    @PrePersist
    @PreUpdate
    private void calculateTotalTime() {
        int prep = (this.prepTime != null) ? this.prepTime : 0;
        int cook = (this.cookTime != null) ? this.cookTime : 0;
        this.totalTime = prep + cook;
    }
}