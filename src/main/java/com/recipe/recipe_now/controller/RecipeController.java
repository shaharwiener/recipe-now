package com.recipe.recipe_now.controller;


import com.recipe.recipe_now.model.RecipeRequest;
import com.recipe.recipe_now.model.RecipeResponse;
import com.recipe.recipe_now.service.RecipeService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe")
    public String getRecipe(@RequestParam List<String> groceries, @RequestParam long time, Model model) throws IOException {
        // Create a RecipeRequest using query parameters and pass it to the service
        RecipeRequest recipeRequest = new RecipeRequest(String.join(",",groceries), time);
        model.addAttribute("recipe", this.recipeService.getRecipe(recipeRequest));
        return "recipe";
    }

}
