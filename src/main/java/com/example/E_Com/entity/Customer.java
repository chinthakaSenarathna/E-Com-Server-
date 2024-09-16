package com.example.E_com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

//    relation
    @OneToMany( mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<CustomerOrder> customerOrders = new HashSet<>();
}
