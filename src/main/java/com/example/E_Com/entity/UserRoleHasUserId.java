package com.example.E_com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserId {
    @Column( name = "user_id", length = 80 )
    private String user;

    @Column( name = "role_id", length = 80 )
    private String userRole;
}
