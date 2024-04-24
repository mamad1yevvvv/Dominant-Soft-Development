package com.example.dominantsoftdevelopment.model;

import com.example.dominantsoftdevelopment.model.baseData.BaseModel;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import com.example.dominantsoftdevelopment.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseModel implements UserDetails {

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private boolean accountNonLocked = true;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    private Status status;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "notification_id" , referencedColumnName = "id")
//    private NotificationApp notificationApp;

    private String firebaseToken;

    @Override
    public String getUsername() {
        return getPhoneNumber();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
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

    public boolean allOk() {
        return isAccountNonExpired() && isAccountNonLocked() && isCredentialsNonExpired() && isEnabled();
    }

    public User(String firstName, String phoneNumber, String password) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
