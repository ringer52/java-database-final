package com.project.code.Repo;

import com.project.code.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// 4. @Repository marks this interface as a Spring Data JPA repository
@Repository
// 1. Extend JpaRepository<Customer, Long> to inherit basic CRUD functionality
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 2. Find a customer by their email address
    Customer findByEmail(String email);

    // 2. Find a customer by their ID
    Customer findById(Long id);

    // 3. Additional custom query methods
    List<Customer> findByName(String name);
    List<Customer> findByPhone(String phone);
}


