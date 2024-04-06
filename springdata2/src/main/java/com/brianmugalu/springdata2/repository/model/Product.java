package com.brianmugalu.springdata2.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
@Entity
@Table(name = "temp_products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int rating;


    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    //@BatchSize(size = 20)
    private List<Review> reviewList;
}
