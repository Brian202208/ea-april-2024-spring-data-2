package com.brianmugalu.springdata2.repository.dto;

import com.brianmugalu.springdata2.repository.model.Review;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends ListCrudRepository<Review,Long> {
}
