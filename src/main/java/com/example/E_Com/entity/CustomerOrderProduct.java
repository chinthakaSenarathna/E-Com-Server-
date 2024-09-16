package com.example.E_com.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity( name = "customer_order_product" )
public class CustomerOrderProduct {
    @Id
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "customer_order_id", referencedColumnName = "property_id" )
    private CustomerOrder customerOrder;

    @Id
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "product_id", referencedColumnName = "property_id" )
    private Product product;
}
