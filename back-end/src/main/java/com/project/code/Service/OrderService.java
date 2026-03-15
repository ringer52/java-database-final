package com.project.code.Service;

import com.project.code.DTO.PlaceOrderRequestDTO;
import com.project.code.Model.Customer;
import com.project.code.Model.Inventory;
import com.project.code.Model.OrderDetails;
import com.project.code.Model.OrderItem;
import com.project.code.Model.Store;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.OrderDetailsRepository;
import com.project.code.Repo.OrderItemRepository;
import com.project.code.Repo.ProductRepository;
import com.project.code.Repo.StoreRepository;
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

        // Step 3: Create OrderDetails object
        OrderDetails orderDetails = new OrderDetails(
                customer,
                store,
                placeOrderRequest.getTotalPrice(),
                LocalDateTime.now()
        );

        // Step 3: Save OrderDetails to database using orderDetailsRepository.save()
        orderDetailsRepository.save(orderDetails);

        // Step 4: Iterate through each purchased product
        for (PlaceOrderRequestDTO.PurchaseProduct purchaseProduct : placeOrderRequest.getPurchaseProducts()) {

            // Step 4a: Find inventory for the product at the store
            Inventory inventory = inventoryRepository.findByProductIdAndStoreId(
                    purchaseProduct.getProductId(),
                    placeOrderRequest.getStoreId()
            );

            // Step 4b: Reduce stock level based on purchased quantity
            int updatedStockLevel = inventory.getStockLevel() - purchaseProduct.getQuantity();
            inventory.setStockLevel(updatedStockLevel);

            // Step 4c: Save updated inventory to database
            inventoryRepository.save(inventory);

            // Step 5: Create OrderItem and associate with OrderDetails
            OrderItem orderItem = new OrderItem(
                    orderDetails,
                    inventory.getProduct(),
                    purchaseProduct.getQuantity(),
                    purchaseProduct.getPrice()
            );

            // Step 5: Save OrderItem to database
            orderItemRepository.save(orderItem);
        }
    }
}
