package com.brianmugalu.springdata2.controller;

import com.brianmugalu.springdata2.repository.model.Product;
import com.brianmugalu.springdata2.repository.model.Review;
import com.brianmugalu.springdata2.repository.model.User;
import com.brianmugalu.springdata2.service.ProductService;
import com.brianmugalu.springdata2.service.ReviewService;
import com.brianmugalu.springdata2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lazy")
public class GeneralController {
    private final ProductService productService;
    private final UserService userService;
    private final ReviewService reviewService;

    //Lazy fetch for the rest except for Join strategy
    //FetchType.LAZY is the default fetch type for @OneToMany and @ManyToMany relationships
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProductProducts();
    }

    @GetMapping("/users")
    public List <User>getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/reviews")
    public List <Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("products/query")
    public List <Product> getAllProductsWithPriceGreaterThan(@RequestParam double price) {
        return productService.getAllProductsWithPriceGreaterThan(price);
    }
}
