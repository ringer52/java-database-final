package com.project.code.Repo;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Custom @Query to fetch inventory by productId and storeId
    @Query("SELECT i FROM Inventory i WHERE i.product.id = :productId AND i.store.id = :storeId")
    Inventory findByProductIdAndStoreId(@Param("productId") Long productId,
                                        @Param("storeId") Long storeId);

    // Method to fetch all inventory entries for a given store ID
    List<Inventory> findByStore_Id(Long storeId);

    // Delete all inventory records for a specific product ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Inventory i WHERE i.product.id = :productId")
    void deleteByProductId(@Param("productId") Long productId);
}
