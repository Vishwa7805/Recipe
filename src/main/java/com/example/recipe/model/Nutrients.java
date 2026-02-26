package com.example.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrients {
    private String calories;
    private String carbohydrateContent;
    private String proteinContent;
    private String fatContent;
}