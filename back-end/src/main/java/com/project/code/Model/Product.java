package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// 7. @Entity marks this class as a JPA entity
@Entity
// 5. @Table enforces uniqueness on the 'sku' column at the database level
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
public class Product {

    // 1. 'id' field - primary key, auto-incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 2. 'name' field - cannot be null
    @NotNull(message = "Name cannot be null")
    private String name;

    // 3. 'category' field - cannot be null
    @NotNull(message = "Category cannot be null")
    private String category;

    // 4. 'price' field - cannot be null
    @NotNull(message = "Price cannot be null")
    private Double price;

    // 5. 'sku' field - cannot be null, must be unique
    @NotNull(message = "SKU cannot be null")
    private String sku;

    // 6. One-to-many relationship with Inventory
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference("inventory-product")
    private List<Inventory> inventories;

    // 8. Getters and Setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public List<Inventory> getInventories() { return inventories; }
    public void setInventories(List<Inventory> inventories) { this.inventories = inventories; }
}
