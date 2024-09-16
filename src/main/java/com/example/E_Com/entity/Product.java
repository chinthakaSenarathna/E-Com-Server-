package com.example.E_com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity( name = "product")
public class Product {
    @Id
    @Column( name = "property_id", length = 80, nullable = false )
    private String propertyId;

    @Column( name = "quantity" )
    private long qty;

    @Column( name = "unit_price" )
    private double unitPrice;

    @Column( name = "description", length = 255 )
    private String description;

//    relation
    @OneToMany( mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private HashSet<ProductImage> images = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private HashSet<CustomerOrderProduct> customerOrderProducts = new HashSet<>();
}
