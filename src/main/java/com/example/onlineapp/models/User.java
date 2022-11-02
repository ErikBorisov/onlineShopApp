package com.example.onlineapp.models;


import com.example.onlineapp.models.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private boolean isActive;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(columnDefinition = "VARCHAR(256)")
    private String activationToken;

    @Column
    private LocalDateTime created;


    @Column
    private LocalDateTime activationDate;


    @Column
    private LocalDateTime updated;


    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }
}

