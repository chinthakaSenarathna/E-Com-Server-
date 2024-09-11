package com.example.E_Com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity( name = "customer" )
public class Customer {
    @Id
    @Column( name = "property_id", length = 80, nullable = false )
    private String propertyId;

    @Column( name = "name", length = 50, nullable = false )
    private String name;

    @Column( name = "email",length = 100, nullable = false, unique = true )
    private String email;

    @Column( name = "phoneNo",length = 20, nullable = false)
    private String phoneNo;

    @Column( name = "address", length = 255, nullable = false) // length = 255 is default
    private String address;

    @Column( name = "is_active", columnDefinition = "TINYINT")
    private boolean isActive;
}
