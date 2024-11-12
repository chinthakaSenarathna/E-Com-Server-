package com.example.E_com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable // act as a composite key
@EqualsAndHashCode
public class UserRoleHasUserId {
    // Default Constructor... is required for creating instance of Embedded Id class by JPA...
    public UserRoleHasUserId(){}

    @Column( name = "user_id", length = 80 )
    private String userId;

    @Column( name = "role_id", length = 80 )
    private String roleId;
}