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
@Entity( name = "customer" )
public class Customer {
    @Id
    @Column( name = "property_id", length = 80, nullable = false )
    private String propertyId;

    @Column( name = "name", length = 50, nullable = false )
    private String name;

    @Column( name = "email", length = 100, nullable = false, unique = true )
    private String email;

    @Column( name = "phoneNo", length = 20, nullable = false )
    private String phoneNo;

    @Column( name = "address", length = 255, nullable = false )
    private String address;

    @Column( name = "is_active", columnDefinition = "TINYINT" )
    private boolean isActive;
}
