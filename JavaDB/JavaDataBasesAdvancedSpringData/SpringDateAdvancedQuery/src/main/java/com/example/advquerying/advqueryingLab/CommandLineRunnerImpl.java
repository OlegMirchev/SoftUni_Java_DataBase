package com.example.advquerying.advqueryingLab;

import com.example.advquerying.advqueryingLab.Service.IngredientService;
import com.example.advquerying.advqueryingLab.Service.LabelService;
import com.example.advquerying.advqueryingLab.Service.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private IngredientService ingredientService;
    private LabelService labelService;
    private ShampooService shampooService;

    @Autowired
    public CommandLineRunnerImpl(IngredientService ingredientService, LabelService labelService, ShampooService shampooService) {
        this.ingredientService = ingredientService;
        this.labelService = labelService;
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

//        01
//        String size = scanner.nextLine();
//        printShampooOrderById(size);

//        02
//        String size = scanner.nextLine();
//        long labelId = Integer.parseInt(scanner.nextLine());
//        printShampooWithSizeOrLabelId(size, labelId);

//        03
//        int price = Integer.parseInt(scanner.nextLine());
//        printShampooWithPriceHigherFromGivenInput(price);

//        04
//        String letter = scanner.nextLine();
//        printAllIngredientsStartingNameWithGivenInput(letter);

//        05
//        List<String> ingredients = List.of("Lavender", "Herbs", "Apple");
//        printAllIngredientsWithGivenListOrderByPrice(ingredients);

//        06
//        double price = Double.parseDouble(scanner.nextLine());
//        printAllShampooCountWithPriceLessThanGivenInput(price);

//        07
//        List<String> ingredients = List.of("Berry", "Mineral-Collagen");
//        printAllShampooIncludedThatIngredients(ingredients);

//        08
//        int number = Integer.parseInt(scanner.nextLine());
//        printAllShampooWhichHaveIngredientsLessThan(number);

//        09
//        String name = scanner.nextLine();
//        deleteIngredientsByName(name);

//        10
//        updateIngredientsByPrice();

//        11
//        List<String> ingredients = List.of("Apple", "Berry", "Active-Caffeine");
//        updateIngredientsByName(ingredients);
    }

    private void updateIngredientsByName(List<String> ingredients) {
        int updateIngredients = this.ingredientService.updateIngredientsByName(ingredients);
        System.out.println(updateIngredients);
    }

    private void updateIngredientsByPrice() {
        int updateIngredients = this.ingredientService.updateIngredientsByPrice();
        System.out.println(updateIngredients);
    }

    private void deleteIngredientsByName(String name) {
        int deleteIngredients = this.ingredientService.deleteIngredientsByNameInput(name);
        System.out.println(deleteIngredients);
    }

    private void printAllShampooWhichHaveIngredientsLessThan(int number) {
        this.shampooService.selectShampooByIngredientsCountLessThan(number).forEach(System.out::println);
    }

    private void printAllShampooIncludedThatIngredients(List<String> ingredients) {
        this.shampooService.selectShampooByIngredientsIncluded(ingredients).forEach(s -> System.out.println(s.getBrand()));
    }

    private void printAllShampooCountWithPriceLessThanGivenInput(double price) {
        int countShampoos = this.shampooService.selectShampooByPriceLessThanInput(price);
        System.out.println(countShampoos);
    }

    private void printAllIngredientsWithGivenListOrderByPrice(List<String> ingredients) {
        this.ingredientService.selectIngredientsWithGivenListAndOrderByPrice(ingredients).forEach(i -> System.out.println(i.getName()));
    }

    private void printAllIngredientsStartingNameWithGivenInput(String letter) {
        this.ingredientService.selectIngredientsStartWithGivenLetter(letter).forEach(i -> System.out.println(i.getName()));
    }

    private void printShampooWithPriceHigherFromGivenInput(int price) {
        this.shampooService.selectShampooByPriceHigherThanInput(price).forEach(System.out::println);
    }

    private void printShampooWithSizeOrLabelId(String size, Long labelId) {
        this.shampooService.selectShampooBySizeOrLabelId(size, labelId).forEach(System.out::println);
    }

    private void printShampooOrderById(String size) {
        this.shampooService.selectShampooBySizeWithId(size).forEach(System.out::println);
    }
}
