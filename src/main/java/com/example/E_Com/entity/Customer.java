package com.example.E_Com.entity;

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
    private String propertyId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private boolean isActive;
}
