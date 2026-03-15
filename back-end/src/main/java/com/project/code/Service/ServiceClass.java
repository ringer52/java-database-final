package com.project.code.Service;

@Service
public class ServiceClass {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public ServiceClass(ProductRepository productRepository,
                        InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // Checks if inventory already exists for a product-store combination
    // Returns false if inventory exists, true if it does not exist
    public boolean validateInventory(Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findByProductIdAndStoreId(
                inventory.getProduct().getId(),
                inventory.getStore().getId()
        );
        return existingInventory == null;
    }

    // Checks if a product already exists by name
    // Returns false if product exists, true if it does not exist
    public boolean validateProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());
        return existingProduct == null;
    }

    // Checks if a product exists by its ID
    // Returns false if product does not exist, true if it exists
    public boolean ValidateProductId(long id) {
        Product existingProduct = productRepository.findById(id);
        return existingProduct != null;
    }

    // Fetches and returns the inventory record for a given product-store combination
    public Inventory getInventoryId(Inventory inventory) {
        Long productId = inventory.getProduct().getId();
        Long storeId = inventory.getStore().getId();
        Inventory foundInventory = inventoryRepository.findByProductIdAndStoreId(productId, storeId);
        return foundInventory;
    }
}
