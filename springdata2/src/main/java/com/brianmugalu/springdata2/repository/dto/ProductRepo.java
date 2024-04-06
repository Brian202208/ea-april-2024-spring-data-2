package com.brianmugalu.springdata2.repository.dto;

import com.brianmugalu.springdata2.repository.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends ListCrudRepository<Product,Long>{
    List<Product> findAllByPriceGreaterThan(double price);
}
