package com.example.advquerying.advqueryingLab.Service.impl;

import com.example.advquerying.advqueryingLab.Service.IngredientService;
import com.example.advquerying.advqueryingLab.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectIngredientsStartWithGivenLetter(String letter) {
        return this.ingredientRepository.findByNameStartingWith(letter);
    }

    @Override
    public List<Ingredient> selectIngredientsWithGivenListAndOrderByPrice(List<String> ingredients) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(ingredients);
    }

    @Override
    public int deleteIngredientsByNameInput(String name) {
        return this.ingredientRepository.deleteIngredientsByName(name);
    }

    @Override
    public int updateIngredientsByPrice() {
        return this.ingredientRepository.updateAllIngredientsByPrice();
    }

    @Override
    public int updateIngredientsByName(List<String> ingredients) {
        return this.ingredientRepository.updateAllIngredientsByName(ingredients);
    }
}
