package com.example.advquerying.advqueryingLab.Service;

import com.example.advquerying.advqueryingLab.entities.Shampoo;

import java.util.List;

public interface ShampooService {

    List<Shampoo> selectShampooBySizeWithId(String size);

    List<Shampoo> selectShampooBySizeOrLabelId(String size, Long labelId);

    List<Shampoo> selectShampooByPriceHigherThanInput(int price);

    int selectShampooByPriceLessThanInput(double price);

    List<Shampoo> selectShampooByIngredientsIncluded(List<String> ingredients);

    List<String> selectShampooByIngredientsCountLessThan(int number);
}
