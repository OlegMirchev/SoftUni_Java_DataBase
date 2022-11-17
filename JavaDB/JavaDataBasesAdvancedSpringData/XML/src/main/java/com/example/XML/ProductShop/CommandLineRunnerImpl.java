package com.example.XML.ProductShop;

import com.example.XML.ProductShop.DTO.CategoriesByProductCountDto;
import com.example.XML.ProductShop.DTO.ProductsWithRangeNotBayerDto;
import com.example.XML.ProductShop.DTO.UsersWithBuyerAndOneSoldProductDto;
import com.example.XML.ProductShop.Service.ProductService;
import com.example.XML.ProductShop.Service.SeedService;
import com.example.XML.ProductShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.math.BigDecimal;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private SeedService seedService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public CommandLineRunnerImpl(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
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

    }

    private void selectCategoryAndProductsDate() throws JAXBException {
        CategoriesByProductCountDto categoriesProductCount = this.productService.findAllCategoriesByProductCount();

        JAXBContext context = JAXBContext.newInstance(CategoriesByProductCountDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(categoriesProductCount, System.out);
    }

    private void selectSoldProductsWithBuyer() throws JAXBException {
        UsersWithBuyerAndOneSoldProductDto usersWithSoldProducts = this.userService.selectAllUsersWithBuyerAndSoldProduct();

        JAXBContext context = JAXBContext.newInstance(UsersWithBuyerAndOneSoldProductDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(usersWithSoldProducts, System.out);
    }

    private void productInRange(BigDecimal fromPrice, BigDecimal toPrice) throws JAXBException {
        ProductsWithRangeNotBayerDto productsIsRange = this.productService.findAllProductsWithRange(fromPrice, toPrice);
        JAXBContext context = JAXBContext.newInstance(ProductsWithRangeNotBayerDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(productsIsRange, System.out);
    }
}
