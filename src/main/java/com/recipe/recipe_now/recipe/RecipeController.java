package com.recipe.recipe_now.recipe;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@Controller
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe")
    public String getRecipe(@RequestParam List<String> groceries, @RequestParam long time, @RequestParam int diners, Model model) throws IOException {
        // Create a RecipeRequest using query parameters and pass it to the service
        RecipeRequest recipeRequest = new RecipeRequest(String.join(",",groceries), time, diners);
        model.addAttribute("recipe", this.recipeService.getRecipe(recipeRequest));
        return "recipe";
    }


//    @GetMapping("/signup")
//    public String signup(@RequestParam String username, @RequestParam String password, Model model) throws IOException {
//        // Create a RecipeRequest using query parameters and pass it to the service
//        RecipeRequest recipeRequest = new RecipeRequest(String.join(",",groceries), time);
//        model.addAttribute("recipe", this.recipeService.getRecipe(recipeRequest));
//        return "recipe";
//    }
}
