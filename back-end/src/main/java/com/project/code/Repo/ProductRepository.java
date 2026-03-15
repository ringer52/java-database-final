package com.project.code.Repo;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products
    List<Product> findAll();

    // Filter products by category
    List<Product> findByCategory(String category);

    // Find products within a price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find a product by SKU
    List<Product> findBySku(String sku);

    // Find a product by name
    Product findByName(String name);

    // Find a product by ID
    Product findById(Long id);

    // Find products by name pattern for a specific store
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List<Product> findByNameLike(@Param("storeId") Long storeId,
                                 @Param("pname") String pname);

    // Find products by name and category for a specific store
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.product.category = :category")
    List<Product> findByNameAndCategory(@Param("storeId") Long storeId,
                                        @Param("pname") String pname,
                                        @Param("category") String category);

    // Find products by category and store ID
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List<Product> findByCategoryAndStoreId(@Param("storeId") Long storeId,
                                           @Param("category") String category);

    // Find products by name pattern ignoring case
    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Product> findProductBySubName(@Param("pname") String pname);

    // Find all products for a specific store
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")
    List<Product> findProductsByStoreId(@Param("storeId") Long storeId);

    // Find products by category and store ID
    @Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")
    List<Product> findProductByCategory(@Param("category") String category,
                                        @Param("storeId") Long storeId);

    // Find products by name pattern and category
    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")
    List<Product> findProductBySubNameAndCategory(@Param("pname") String pname,
                                                  @Param("category") String category);
}
