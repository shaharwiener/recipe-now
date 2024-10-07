package com.recipe.recipe_now.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private ChatClient chatClient;

    public RecipeService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateJoke(){
//        this.chatClient.call("Tell me a joke");
        return this.chatClient.prompt().user("tell me a joke").call().content();
    }



//    @Autowired
//    private OpenAiTemplate openAiTemplate;
//
//    public RecipeResponse generateRecipe(RecipeRequest request) {
//        String prompt = createPrompt(request);
//        String recipeInstructions = callOpenAiForRecipe(prompt);
//
//        return new RecipeResponse("AI-Generated Recipe", request.getTimeAvailable(), recipeInstructions);
//    }
//
//    private String createPrompt(RecipeRequest request) {
//        return "Create a recipe based on the following details: " +
//                "Groceries: " + request.getGroceries() + ", " +
//                "Time: " + request.getTimeAvailable() + " minutes, " +
//                "Type: " + request.getType() + ". " +
//                "Provide a title, instructions, and estimated time to cook.";
//    }
//
//    private String callOpenAiForRecipe(String prompt) {
//        String recipeInstructions = openAiTemplate.completion(prompt);
//        return recipeInstructions.trim();
//    }
}

