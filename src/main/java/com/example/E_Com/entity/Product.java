package com.example.E_com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
}
