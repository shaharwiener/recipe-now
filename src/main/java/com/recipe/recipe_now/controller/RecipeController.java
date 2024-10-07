package com.recipe.recipe_now.controller;


import com.recipe.recipe_now.service.RecipeService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/joke-service/simple")
    public Map<String, String> tellSimpleJoke() {

        return Map.of("generation", this.recipeService.generateJoke());
    }

}
