package com.example.advquerying.advqueryingLab.Service.impl;

import com.example.advquerying.advqueryingLab.Service.ShampooService;
import com.example.advquerying.advqueryingLab.entities.Shampoo;
import com.example.advquerying.advqueryingLab.entities.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> selectShampooBySizeWithId(String input) {
        Size size = Size.valueOf(input);

        return this.shampooRepository.findShampooBySizeAndOrderById(size);
    }

    @Override
    public List<Shampoo> selectShampooBySizeOrLabelId(String input, Long labelId) {
        Size size = Size.valueOf(input);

        return this.shampooRepository.findShampooWithGivenSizeOrLabelId(size, labelId);
    }

    @Override
    public List<Shampoo> selectShampooByPriceHigherThanInput(int price) {
        BigDecimal bigDecimal = BigDecimal.valueOf(price);

        return this.shampooRepository.findShampooGreaterThanOrderByPriceDesc(bigDecimal);
    }

    @Override
    public int selectShampooByPriceLessThanInput(double price) {
        BigDecimal bigDecimal = BigDecimal.valueOf(price);

        return this.shampooRepository.countByPriceLessThan(bigDecimal);
    }

    @Override
    public List<Shampoo> selectShampooByIngredientsIncluded(List<String> ingredients) {
        return this.shampooRepository.findAllShampooIncludedThatIngredients(ingredients);
    }

    @Override
    public List<String> selectShampooByIngredientsCountLessThan(int number) {
        return this.shampooRepository.findAllShampoosWhichCountIngredientsLessThan(number);
    }
}
