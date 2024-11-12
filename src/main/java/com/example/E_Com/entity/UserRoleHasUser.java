package com.example.E_com.entity;

import jakarta.persistence.*;
import lombok.*;

@Table( name = "user_role_has_user" )
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleHasUser {
    @EmbeddedId // composite key represent...
    private UserRoleHasUserId userRoleHasUserId = new UserRoleHasUserId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;
}