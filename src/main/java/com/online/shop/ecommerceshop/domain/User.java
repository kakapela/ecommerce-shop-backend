package com.online.shop.ecommerceshop.domain;

import com.online.shop.ecommerceshop.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Date lastLoginDate;
    private Date registrationDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isActive;
    private boolean isNotLocked;
}
