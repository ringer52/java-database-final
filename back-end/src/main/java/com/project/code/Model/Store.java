package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// 6. @Entity marks this class as a JPA entity
@Entity
public class Store {

    // 1. 'id' field - primary key, auto-incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 2. 'name' field - cannot be null
    @NotNull(message = "Name cannot be null")
    private String name;

    // 3. 'address' field - cannot be null or blank
    @NotNull(message = "Address cannot be null")
    @NotBlank(message = "Address cannot be blank")
    private String address;

    // 4. One-to-many relationship with Inventory
    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    @JsonManagedReference("inventory-store")
    private List<Inventory> inventories;

    // 5. Custom constructor
    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Default constructor (required by JPA)
    public Store() {}

    // 7. Getters and Setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<Inventory> getInventories() { return inventories; }
    public void setInventories(List<Inventory> inventories) { this.inventories = inventories; }
}

