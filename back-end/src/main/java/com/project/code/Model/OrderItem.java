package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

// 7. @Entity marks this class as a JPA entity
@Entity
public class OrderItem {

    // 1. 'id' field - primary key, auto-incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 'order' field - many-to-one relationship with OrderDetails
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonManagedReference
    private OrderDetails order;

    // 3. 'product' field - many-to-one relationship with Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    // 4. 'quantity' field - quantity of the product in the order
    private Integer quantity;

    // 5. 'price' field - price of the product at the time of the order
    private Double price;

    // 6. Constructors

    // No-argument constructor (required by JPA)
    public OrderItem() {}

    // Parameterized constructor
    public OrderItem(OrderDetails order, Product product, Integer quantity, Double price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // 8. Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderDetails getOrder() { return order; }
    public void setOrder(OrderDetails order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
