package com.recipe.recipe_now.recipe;

public class RecipePrompts {

    static String createGetRecipeOptionsPrompt(RecipeRequest request) {
        return "Suggest four options of recipes based on the following details: " +
                "Groceries: " + request.groceries() +
                " and cooking time is up to " + request.time() + " minutes," +
                " and number of diners is ." + request.diners() +
                " The groceries input will be in Hebrew " +
                " and the output should be the titles of the four options in Hebrew." +
                " Please provide a JSON response with the following fields:" +
                " {\"option1\": \"\", \"option2\": \"\", \"option3\": \"\", \"option4\": \"\"}.";
    }

    static String createGetRecipePrompt(RecipeRequest request) {
        return "Create a recipe for with this Hebrew name " + request.recipeTitle()  + " based on the following details: " +
                "The only groceries to be used are: " + request.groceries() +
                " and cooking time is up to " + request.time() + " minutes," +
                " and number of diners is ." + request.diners() +
                " The groceries input will be in Hebrew and the output should also be in Hebrew. " +
                " The groceries output should include the amount needed for the given diners " +
                "Please provide a JSON response with the following fields: " +
                "{\"title\": \"\", \"diners\": \"\", \"groceries\": [], \"time\": \"\", \"instructions\": []}.";
    }

}
