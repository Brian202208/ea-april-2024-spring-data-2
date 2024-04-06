package com.brianmugalu.springdata2.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Table(name = "temp_reviews")
@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    //@BatchSize(size = 20)
    @JoinColumn(name = "id_product")
    @JsonIgnore
    private Product product;
}
