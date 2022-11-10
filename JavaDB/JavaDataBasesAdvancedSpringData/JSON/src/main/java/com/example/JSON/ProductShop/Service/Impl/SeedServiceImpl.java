package com.example.JSON.ProductShop.Service.Impl;

import com.example.JSON.ProductShop.DTO.SeedCategoryDto;
import com.example.JSON.ProductShop.DTO.SeedProductDto;
import com.example.JSON.ProductShop.DTO.SeedUserDto;
import com.example.JSON.ProductShop.Repository.CategoryRepository;
import com.example.JSON.ProductShop.Repository.ProductRepository;
import com.example.JSON.ProductShop.Repository.UserRepository;
import com.example.JSON.ProductShop.Service.SeedService;
import com.example.JSON.ProductShop.entities.Category;
import com.example.JSON.ProductShop.entities.Product;
import com.example.JSON.ProductShop.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String RESOURCES_PATH = "src//main//resources//files//";
    private final static String USER_PATH = "users.json";
    private final static String PRODUCT_PATH = "products.json";
    private final static String CATEGORY_PATH = "categories.json";

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;
    private Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + USER_PATH);

        SeedUserDto[] seedUserDto = this.gson.fromJson(fileReader, SeedUserDto[].class);

        List<User> collectUsers = Arrays.stream(seedUserDto).map(dto -> this.modelMapper.map(dto, User.class)).collect(Collectors.toList());

        this.userRepository.saveAll(collectUsers);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + PRODUCT_PATH);

        SeedProductDto[] seedProductDto = this.gson.fromJson(fileReader, SeedProductDto[].class);

        List<Product> collectProduct = Arrays.stream(seedProductDto)
                .map(dto -> this.modelMapper.map(dto, Product.class))
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .map(this::setRandomCategory)
                .collect(Collectors.toList());

        this.productRepository.saveAll(collectProduct);
    }


    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CATEGORY_PATH);

        SeedCategoryDto[] seedCategoryDto = this.gson.fromJson(fileReader, SeedCategoryDto[].class);

        List<Category> collectCategories = Arrays.stream(seedCategoryDto).map(dto -> this.modelMapper.map(dto, Category.class)).collect(Collectors.toList());

        this.categoryRepository.saveAll(collectCategories);
    }

    private Product setRandomBuyer(Product product) {
        Optional<User> buyer = getRandomUser();

        product.setBuyer(buyer.get());

        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();

        product.setSeller(seller.get());

        return product;
    }

    private Product setRandomCategory(Product product) {
        long countCategory = this.categoryRepository.count();

        int categories = new Random().nextInt((int) countCategory);

        Set<Category> listCategories = new LinkedHashSet<>();

        for (int i = 0; i < categories; i++) {
            int randomId = new Random().nextInt((int) countCategory) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            listCategories.add(randomCategory.get());
        }

        product.setCategories(listCategories);

        return product;
    }

    private Optional<User> getRandomUser() {
        long countUsers = this.userRepository.count();

        int randomId = new Random().nextInt((int) countUsers) + 1;

        return this.userRepository.findById(randomId);
    }
}
