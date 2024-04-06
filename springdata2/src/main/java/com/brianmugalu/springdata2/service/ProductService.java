package com.brianmugalu.springdata2.service;

import com.brianmugalu.springdata2.repository.dto.ProductRepo;
import com.brianmugalu.springdata2.repository.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public List<Product> getAllProductProducts() {
       return productRepo.findAll();
    }

    public List<Product> getAllProductsWithPriceGreaterThan(double price) {
        return productRepo.findAllByPriceGreaterThan(price);
    }
}
