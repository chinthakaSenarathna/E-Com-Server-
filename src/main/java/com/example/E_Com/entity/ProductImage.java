package com.example.E_com.entity;

import jakarta.persistence.*;
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

//    relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "property_id")
    private Product product;
}
