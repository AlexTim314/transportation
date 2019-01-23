package org.ivc.transportation.entities;

import java.util.Set;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "department", "transportDep"})
@ToString(exclude = {"roles", "department", "transportDep"})
@Entity
@Table(name = "app_user",
        uniqueConstraints = {
            @UniqueConstraint(name = "APP_USER_UK", columnNames = "username")})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @Column(name = "full_name", length = 128, nullable = false)
    private String fullName;

    @Column(name = "encrypted_password", length = 128, nullable = false)
    private String password;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

    @ManyToOne
    private Department department;

    @ManyToOne
    private TransportDep transportDep;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRole> roles;

    @Transient
    private boolean selected;

    public AppUser(String username, String fullName, String password, Department department, TransportDep transportDep, Set<AppRole> roles) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.enabled = true;
        this.department = department;
        this.transportDep = transportDep;
        this.roles = roles;
    }    

}
