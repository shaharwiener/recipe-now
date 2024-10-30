package com.recipe.recipe_now.recipe;


public record RecipeRequest (String recipeTitle,
                             String groceries,
                             long time,
                             int diners) {}

