package com.project.code.Repo;

import com.project.code.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository marks this interface as a Spring Data JPA repository
@Repository
// 1. Extend JpaRepository<OrderDetails, Long> to inherit basic CRUD functionality
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    // 2. No custom methods required - JpaRepository provides all basic CRUD operations:
    // save(), findById(), findAll(), deleteById(), count(), existsById(), etc.
}
