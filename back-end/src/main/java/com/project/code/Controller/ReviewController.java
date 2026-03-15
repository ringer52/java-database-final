package com.project.code.Controller;

import com.project.code.Model.Customer;
import com.project.code.Model.Review;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // GET /reviews - returns ALL reviews using reviewRepository.findAll()
    @GetMapping
    public Map<String, Object> getAllReviews() {
        Map<String, Object> response = new HashMap<>();
        List<Review> allReviews = reviewRepository.findAll();
        response.put("reviews", allReviews);
        return response;
    }

    // GET /{storeId}/{productId} - fetches reviews AND includes customer names in response
    @GetMapping("/{storeId}/{productId}")
    public Map<String, Object> getReviews(@PathVariable Long storeId,
                                           @PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();

        // Step 1: Fetch all reviews for the specific product in the store
        List<Review> reviews = reviewRepository.findByStoreIdAndProductId(storeId, productId);

        // Step 2: Build response list including customer names
        List<Map<String, Object>> filteredReviews = new ArrayList<>();

        for (Review review : reviews) {
            Map<String, Object> reviewMap = new HashMap<>();

            // Add comment from review
            reviewMap.put("comment", review.getComment());

            // Add rating from review
            reviewMap.put("rating", review.getRating());

            // Fetch customer name using customerId from review
            Long customerId = review.getCustomerId();
            Customer customer = customerRepository.findById(customerId);

            // Add customerName to response - "Unknown" if customer not found
            if (customer != null) {
                String customerName = customer.getName();
                reviewMap.put("customerName", customerName);
            } else {
                reviewMap.put("customerName", "Unknown");
            }

            filteredReviews.add(reviewMap);
        }

        response.put("reviews", filteredReviews);
        return response;
    }
}
