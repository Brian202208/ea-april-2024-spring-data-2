package com.brianmugalu.springdata2.service;

import com.brianmugalu.springdata2.repository.dto.ReviewRepo;
import com.brianmugalu.springdata2.repository.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepo reviewRepo;

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }
}
