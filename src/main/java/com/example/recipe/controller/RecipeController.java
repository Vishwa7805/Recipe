package com.example.recipe.controller;

import com.example.recipe.model.Recipe;
import com.example.recipe.repository.RecipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping("/add-recipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe saved = recipeRepository.save(recipe);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/")
    public ResponseEntity<String> uploadRecipes(@RequestParam("file") MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Recipe> map = mapper.readValue(
                    file.getInputStream(),
                    new TypeReference<Map<String, Recipe>>() {}
            );

            recipeRepository.saveAll(map.values());
            return ResponseEntity.ok("Successfully uploaded.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/top")
    public ResponseEntity<Map<String, Object>> getTopRated(@RequestParam(defaultValue = "5") int limit) {
        List<Recipe> recipes = recipeRepository.findTopRatedRecipe(limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", recipes);

        return ResponseEntity.ok(response);
    }
}

