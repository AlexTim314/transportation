package org.ivc.transportation.entities;

import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Sokolov Slava
 */
@Data
@Entity
@Table(name = "app_role",
        uniqueConstraints = {
            @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name")})
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

}
