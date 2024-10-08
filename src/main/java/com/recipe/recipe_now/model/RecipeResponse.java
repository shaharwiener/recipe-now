package com.recipe.recipe_now.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RecipeResponse (
        @JsonProperty(value = "title", required = true) String title,
        @JsonProperty(value = "groceries", required = true) List<String> groceries,
        @JsonProperty(value = "time", required = true) long time,
        @JsonProperty(value = "instructions", required = true) List<String> instructions) {}


