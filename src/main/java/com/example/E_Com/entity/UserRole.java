package com.example.E_com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table( name = "user_role" )
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @Column( name = "role_id", length = 80 )
    private String roleId;

    @Column( name = "role_name", length = 100 )
    private String roleName;

    @Column( name = "role_description", length = 200 )
    private String roleDescription;
}
