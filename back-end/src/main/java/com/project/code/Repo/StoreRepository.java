package com.project.code.Repo;

import com.project.code.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository marks this interface as a Spring Data JPA repository
@Repository
// 1. Extend JpaRepository<Store, Long> to inherit basic CRUD functionality
public interface StoreRepository extends JpaRepository<Store, Long> {

    // 2. Find a store by its ID
    Store findByid(Long id);

    // 2. Find stores whose name contains a given substring (case-insensitive)
    @Query("SELECT s FROM Store s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Store> findBySubName(@Param("pname") String pname);
}
