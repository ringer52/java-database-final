package com.project.code.Service;

import com.project.code.Model.*;
import com.project.code.Repo.*;
import com.project.code.DTO.PlaceOrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrder(PlaceOrderRequestDTO placeOrderRequest) {

        // Step 1: Retrieve or Create the Customer
        Customer customer = customerRepository.findByEmail(placeOrderRequest.getEmail());
        if (customer == null) {
            customer = new Customer();
            customer.setName(placeOrderRequest.getName());
            customer.setEmail(placeOrderRequest.getEmail());
            customer.setPhone(placeOrderRequest.getPhone());
            customerRepository.save(customer);
        }

        // Step 2: Retrieve the Store
        Optional<Store> storeOptional = storeRepository.findById(placeOrderRequest.getStoreId());
        if (!storeOptional.isPresent()) {
            throw new RuntimeException("Store not found with ID: " + placeOrderRequest.getStoreId());
        }
        Store store = storeOptional.get();

        // Step 3: Create OrderDetails and save using orderDetailsRepos
