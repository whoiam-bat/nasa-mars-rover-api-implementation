package ua.com.demo.spacey.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "login")
    @NotBlank(message = "Username cannot be empty")
    private String login;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{6,}$",
            message = "Password should contain at least 6 characters (a-zA-Z0-9)")
    private String password;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "preference_id")
    @ToString.Exclude
    private Preference preference;

    public Customer(String login, String password, String roleName, LocalDate dateCreated, LocalDate dateUpdated) {
        this.login = login;
        this.password = password;
        this.roleName = roleName;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roleName));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
