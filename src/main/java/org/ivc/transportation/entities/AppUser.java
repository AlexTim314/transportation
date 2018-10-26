package org.ivc.transportation.entities;

import java.util.HashSet;
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
@Table(name = "App_User",
        uniqueConstraints = {
            @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name")})
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String password;

    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;

    @ManyToOne
    private Department department;

    @ManyToOne
    private TransportDep transportDep;

//    @ManyToOne
//    private Department department;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "User_Id"), inverseJoinColumns = @JoinColumn(name = "Role_Id"))
    private Set<AppRole> roles;

    @Transient
    private boolean selected;

    public AppUser(Long userId, String userName, String encrytedPassword, boolean enabled, Set<AppRole> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = encrytedPassword;
        this.enabled = enabled;
        this.roles = roles;
    }

    public AppUser(String userName, String password, boolean enabled, HashSet<AppRole> roles) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

}
