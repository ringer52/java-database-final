package com.project.code.Service;

import com.project.code.Model.Inventory;
import com.project.code.Model.Product;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    // validateInventory - checks if inventory entry already exists for a product-store combination
    // Returns false if inventory already exists for the product-store combination
    // Returns true if no inventory exists for the product-store combination
    public boolean validateInventory(Inventory inventory) {
        Long productId = inventory.getProduct().getId();
        Long storeId = inventory.getStore().getId();
        Inventory existingInventory = inventoryRepository.findByProductIdAndStoreId(
                productId,
                storeId
        );
        if (existingInventory != null) {
            return false;
        }
        return true;
    }

    // validateProduct - checks if a product already exists by name
    // Returns false if product with same name already exists
    // Returns true if no product with same name exists
    public boolean validateProduct(Product product) {
        String productName = product.getName();
        Product existingProduct = productRepository.findByName(productName);
        if (existingProduct != null) {
            return false;
        }
        return true;
    }

    // ValidateProductId - checks if a product exists by its ID
    // Returns false if product does not exist with the given ID
    // Returns true if product exists with the given ID
    public boolean ValidateProductId(long id) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            return false;
        }
        return true;
    }

    // getInventoryId - fetches and returns inventory record using both product ID and store ID
    public Inventory getInventoryId(Inventory inventory) {
        Long productId = inventory.getProduct().getId();
        Long storeId = inventory.getStore().getId();
        Inventory foundInventory = inventoryRepository.findByProductIdAndStoreId(
                productId,
                storeId
        );
        return foundInventory;
    }
}
