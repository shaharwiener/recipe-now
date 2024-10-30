package com.recipe.recipe_now.recipe;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }



    @GetMapping("/recipe/options")
    public RecipeOptionsResponse getRecipeOptions(@RequestParam List<String> groceries, @RequestParam long time, @RequestParam int diners) throws IOException {
        RecipeRequest recipeRequest = new RecipeRequest("", String.join(",",groceries), time, diners);
        return this.recipeService.getRecipeOptions(recipeRequest);
    }

    @GetMapping("/recipe")
    public RecipeResponse getRecipe(@RequestParam String title, @RequestParam List<String> groceries, @RequestParam long time, @RequestParam int diners) throws IOException {
        // Create a RecipeRequest using query parameters and pass it to the service
        RecipeRequest recipeRequest = new RecipeRequest(title, String.join(",",groceries), time, diners);
        return this.recipeService.getRecipe(recipeRequest);
    }

}
