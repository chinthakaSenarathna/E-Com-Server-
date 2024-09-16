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

//    Large object in Binary
    @Lob
    @Column( name = "directory", nullable = false )
    private byte[] directory;

    @Lob
    @Column( name = "resourceUrl", nullable = false )
    private byte[] resourceUrl;

    @Lob
    @Column( name = "hash", nullable = false )
    private byte[] hash;

    @Lob
    @Column( name = "file_name", nullable = false )
    private byte[] fileNme;

//    relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "property_id")
    private Product product;
}
