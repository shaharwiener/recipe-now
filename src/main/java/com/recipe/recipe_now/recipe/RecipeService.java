package com.recipe.recipe_now.recipe;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final ChatClient chatClient;

    public RecipeService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public RecipeResponse getRecipe(RecipeRequest recipeRequest) throws IOException {
        var outputConverter = new BeanOutputConverter<>(RecipeResponse.class);
        var jsonSchema = outputConverter.getJsonSchema();
        String userPrompt = createPrompt(recipeRequest);
        System.out.println("-----------> userPrompt:" + userPrompt);

        Prompt prompt = new Prompt(userPrompt,
                OpenAiChatOptions.builder()
                        .withResponseFormat(new OpenAiApi.ChatCompletionRequest.ResponseFormat(OpenAiApi.ChatCompletionRequest.ResponseFormat.Type.JSON_SCHEMA, jsonSchema))
                        .build());

        String response = this.chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getContent();

        System.out.println("-----------> response:" + response);
        // Parse the JSON response
        return parseJsonResponse(response);
    }

    private String createPrompt(RecipeRequest request) {
        return "Create a recipe based on the following details: " +
                "Groceries: " + request.groceries() +
                " and cooking time is up to " + request.time() + " minutes," +
                " and number of diners is ." + request.diners() +
                " The groceries input will be in Hebrew and the output should also be in Hebrew. " +
                " The groceries output should include the amount needed for the given diners " +
                "Please provide a JSON response with the following fields: " +
                "{\"title\": \"\", \"diners\": \"\", \"groceries\": [], \"time\": \"\", \"instructions\": []}.";
    }

    private RecipeResponse parseJsonResponse(String response) throws IOException {
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
