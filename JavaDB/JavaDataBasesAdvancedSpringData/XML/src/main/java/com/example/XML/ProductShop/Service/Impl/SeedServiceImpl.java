package com.example.XML.ProductShop.Service.Impl;

import com.example.XML.ProductShop.DTO.CategoryDto;
import com.example.XML.ProductShop.DTO.SeedCategoryDto;
import com.example.XML.ProductShop.DTO.SeedProductDto;
import com.example.XML.ProductShop.DTO.SeedUserDto;
import com.example.XML.ProductShop.Repository.CategoryRepository;
import com.example.XML.ProductShop.Repository.ProductRepository;
import com.example.XML.ProductShop.Repository.UserRepository;
import com.example.XML.ProductShop.Service.SeedService;
import com.example.XML.ProductShop.entities.Category;
import com.example.XML.ProductShop.entities.Product;
import com.example.XML.ProductShop.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String RESOURCES_PATH = "src//main//resources//files//";
    private final static String USER_PATH = "users.xml";
    private final static String PRODUCT_PATH = "products.xml";
    private final static String CATEGORY_PATH = "categories.xml";

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.modelMapper = new ModelMapper();

    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + USER_PATH);

        JAXBContext context = JAXBContext.newInstance(SeedUserDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        SeedUserDto unmarshal = (SeedUserDto) unmarshaller.unmarshal(fileReader);

        List<User> importUsers = unmarshal.getUsers().stream().map(u -> this.modelMapper.map(u, User.class)).collect(Collectors.toList());

        this.userRepository.saveAll(importUsers);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + PRODUCT_PATH);

        JAXBContext context = JAXBContext.newInstance(SeedProductDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        SeedProductDto unmarshal = (SeedProductDto) unmarshaller.unmarshal(fileReader);

        List<Product> importProducts = unmarshal.getProducts().stream().map(p -> this.modelMapper.map(p, Product.class))
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .map(this::setRandomCategory)
                .collect(Collectors.toList());

        this.productRepository.saveAll(importProducts);
    }


    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CATEGORY_PATH);

        JAXBContext context = JAXBContext.newInstance(SeedCategoryDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        SeedCategoryDto unmarshal = (SeedCategoryDto) unmarshaller.unmarshal(fileReader);

        List<Category> importCategories = unmarshal.getCategories().stream().map(c -> this.modelMapper.map(c, Category.class)).collect(Collectors.toList());

        this.categoryRepository.saveAll(importCategories);
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
