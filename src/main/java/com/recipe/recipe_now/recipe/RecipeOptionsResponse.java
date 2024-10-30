package com.recipe.recipe_now.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecipeOptionsResponse (
        @JsonProperty(value = "option1", required = true) String option1,
        @JsonProperty(value = "option2", required = true) String option2,
        @JsonProperty(value = "option3", required = true) String option3,
        @JsonProperty(value = "option4", required = true) String option4
) {}

