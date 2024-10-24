package com.example.E_com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table( name = "user_role" )
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {
    @Id
    @Column( name = "role_id", length = 80 )
    private String roleId;

    @Column( name = "role_name", length = 100 )
    private String roleName;

    @Column( name = "role_description", length = 200 )
    private String roleDescription;

//  not given mapped data, only given if we requested.... -> FetchType.LAZY
    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private Set<UserRoleHasUser> userRoleHasUsers;
}
