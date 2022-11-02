package com.example.onlineapp.models;

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
@Table(name = "category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column
    private LocalDateTime created;

    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
    }

}
