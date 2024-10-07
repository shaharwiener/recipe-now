package com.recipe.recipe_now;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.recipe.recipe_now.controller","com.recipe.recipe_now.service"})
@SpringBootApplication
public class RecipeNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeNowApplication.class, args);
	}

}
