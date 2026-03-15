package com.project.code.Repo;

import com.project.code.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository marks this interface as a Spring Data MongoDB repository
@Repository
// 1. Extend MongoRepository<Review, String> to inherit basic CRUD functionality for MongoDB
public interface ReviewRepository extends MongoRepository<Review, String> {

    // 2. Find reviews by store ID and product ID
    List<Review> findByStoreIdAndProductId(Long storeId, Long productId);
}
