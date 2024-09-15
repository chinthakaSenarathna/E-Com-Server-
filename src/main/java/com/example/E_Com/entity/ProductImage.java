package com.example.E_com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity( name = "product_image" )
public class ProductImage {
    @Id
    @Column( name = "property_id", length = 80, nullable = false )
    private String propertyId;
}
