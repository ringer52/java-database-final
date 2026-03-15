package com.project.code.Controller;

import com.project.code.Model.Customer;
import com.project.code.Model.Review;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // GET /reviews - returns all reviews using reviewRepository.findAll()
    @GetMapping
    public Map<String, Object> getAllReviews() {
        Map<String, Object> response = new HashMap<>();
        List<Review> reviews = reviewRepository.findAll();
        response.put("reviews", reviews);
        return response;
    }

    // GET /reviews/{storeId}/{productId} - fetches reviews and includes customer names
    @GetMapping("/{storeId}/{productId}")
    public Map<String, Object> getReviews(@PathVariable Long storeId,
                                           @PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();

        // Fetch all reviews for the specific product in the store
        List<Review> reviews = reviewRepository.findByStoreIdAndProductId(storeId, productId);

        // Build response with comment, rating and customer name
        List<Map<String, Object>> filteredReviews = new ArrayList<>();

        for (Review review : reviews) {
            Map<String, Object> reviewMap = new HashMap<>();

            reviewMap.put("comment", review.getComment());
            reviewMap.put("rating", review.getRating());

            // Fetch customer name using customer ID from the review
            Customer customer = customerRepository.findById(review.getCustomerId());
            if (customer != null) {
                reviewMap.put("customerName", customer.getName());
            } else {
                reviewMap.put("customerName", "Unknown");
            }

            filteredReviews.add(reviewMap);
        }

        response.put("
