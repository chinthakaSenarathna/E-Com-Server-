package com.example.E_com.entity;

import com.example.E_com.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity( name = "customer_order" )
public class CustomerOrder {
    @Id
    @Column( name = "property_id", nullable = false, length = 80 )
    private String propertyId;

    @Column( name = "created_date", columnDefinition = "DATETIME", nullable = false )
    private Date createdDate;

    @Column( name = "total_amount" )
    private double totalAmount;

    @Column( name = "payment_type" )
    private PaymentType paymentType;

//    relation
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "customer_id", referencedColumnName = "property_id")
    private Customer customer;

    @OneToMany( mappedBy = "customerOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<CustomerOrderProduct> customerOrderProducts = new HashSet<>();
}
