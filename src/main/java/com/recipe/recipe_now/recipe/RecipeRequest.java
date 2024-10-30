package com.recipe.recipe_now.recipe;


public record RecipeRequest (String groceries,
                             long time,
                             int diners) {}

