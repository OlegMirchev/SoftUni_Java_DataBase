package com.example.advquerying.advqueryingLab.Service;

import com.example.advquerying.advqueryingLab.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectIngredientsStartWithGivenLetter(String letter);

    List<Ingredient> selectIngredientsWithGivenListAndOrderByPrice(List<String> ingredients);

    int deleteIngredientsByNameInput(String name);

    int updateIngredientsByPrice();

    int updateIngredientsByName(List<String> ingredients);
}
