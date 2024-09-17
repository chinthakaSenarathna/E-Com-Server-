package com.example.E_com.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class CustomerOrderProductId implements Serializable {
    // Getters and setters
    private String customerOrder;
    private String product;

    // Constructor with fields
    public CustomerOrderProductId(String customerOrder, String product) {
        this.customerOrder = customerOrder;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderProductId that = (CustomerOrderProductId) o;
        return Objects.equals(customerOrder, that.customerOrder) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrder, product);
    }
}
