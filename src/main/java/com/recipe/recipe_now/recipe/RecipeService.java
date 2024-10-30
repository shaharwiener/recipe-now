package com.recipe.recipe_now.recipe;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipe_now.ai.AiService;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final AiService aiService;

    public RecipeService(AiService aiService) {
        this.aiService = aiService;
    }

    public RecipeOptionsResponse getRecipeOptions(RecipeRequest recipeRequest) throws IOException {
        var outputConverter = new BeanOutputConverter<>(RecipeOptionsResponse.class);
        var jsonSchema = outputConverter.getJsonSchema();
        String getRecipeOptionsPrompt = RecipePrompts.createGetRecipeOptionsPrompt(recipeRequest);
        String response = this.aiService.executePrompt(getRecipeOptionsPrompt, jsonSchema);

        return createRecipeOptionsResponse(response);
    }

    private RecipeOptionsResponse createRecipeOptionsResponse(String response) throws IOException {
        // Use Jackson ObjectMapper to parse the response as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        String option1 = jsonResponse.path("option1").asText();
        String option2 = jsonResponse.path("option2").asText();
        String option3 = jsonResponse.path("option3").asText();
        String option4 = jsonResponse.path("option4").asText();

        return new RecipeOptionsResponse(option1,option2,option3,option4);

    }

    public RecipeResponse getRecipe(RecipeRequest recipeRequest) throws IOException {
        var outputConverter = new BeanOutputConverter<>(RecipeResponse.class);
        String jsonSchema = outputConverter.getJsonSchema();
        String getRecipePrompt = RecipePrompts.createGetRecipePrompt(recipeRequest);
        String response = this.aiService.executePrompt(getRecipePrompt, jsonSchema);

        return createRecipeResponse(response);
    }

    private RecipeResponse createRecipeResponse(String response) throws IOException {
        // Use Jackson ObjectMapper to parse the response as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        String title = jsonResponse.path("title").asText();

        // Parse the groceries field as a List<String>
        List<String> groceries = new ArrayList<>();
        for (JsonNode grocery : jsonResponse.path("groceries")) {
            groceries.add(grocery.asText());
        }

        int diners = jsonResponse.path("diners").asInt();
        long time = jsonResponse.path("time").asLong();

        // Parse the instructions field as a List<String>
        List<String> instructions = new ArrayList<>();
        for (JsonNode instruction : jsonResponse.path("instructions")) {
            instructions.add(instruction.asText());
        }

        return new RecipeResponse(title, diners, groceries, time, instructions);
    }

}

