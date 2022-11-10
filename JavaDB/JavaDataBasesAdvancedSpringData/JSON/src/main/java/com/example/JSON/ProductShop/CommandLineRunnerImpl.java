package com.example.JSON.ProductShop;

import com.example.JSON.ProductShop.DTO.CategoryDateDto;
import com.example.JSON.ProductShop.DTO.ProductRangeNotBuyerDto;
import com.example.JSON.ProductShop.DTO.UserAndProductsSoldDto;
import com.example.JSON.ProductShop.DTO.UserWithSoldProductsDto;
import com.example.JSON.ProductShop.Service.ProductService;
import com.example.JSON.ProductShop.Service.SeedService;
import com.example.JSON.ProductShop.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private SeedService seedService;
    private ProductService productService;
    private UserService userService;

    private Gson gson;

    @Autowired
    public CommandLineRunnerImpl(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();

//        Query 1 – Products in Range
//        productInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

//        Query 2 – Successfully Sold Products
//        selectSoldProductsWithBuyer();

//        Query 3 – Categories by Products Count
//        selectCategoryAndProductsDate();

        // TODO: This exercise is uncompleted because I'm having trouble finding COUNT of products
//        Query 4 – Users and Products
//        selectUsersAndProducts();
    }

    private void selectUsersAndProducts() {
        List<UserAndProductsSoldDto> allUsersWithProductsSold = this.userService.findAllUsersWithProductsSold();
        System.out.println("{\n" +
                "userCount:" + allUsersWithProductsSold.size() + ",\n" +
                "user:\n" + this.gson.toJson(allUsersWithProductsSold) +
                "\n}");

    }

    private void selectCategoryAndProductsDate() {
        List<CategoryDateDto> categoryWithProductsDate = this.productService.findCategoryWithProductsDate();

        System.out.println(this.gson.toJson(categoryWithProductsDate));
    }

    private void selectSoldProductsWithBuyer() {
        List<UserWithSoldProductsDto> userWithThemSoldProducts = this.userService.findUserWithThemSoldProducts();

        System.out.println(this.gson.toJson(userWithThemSoldProducts));
    }

    private void productInRange(BigDecimal fromPrice, BigDecimal toPrice) {
        List<ProductRangeNotBuyerDto> listProductSell = this.productService.selectProductInRangeNotBuyer(fromPrice, toPrice);

        System.out.println(this.gson.toJson(listProductSell));
    }
}
