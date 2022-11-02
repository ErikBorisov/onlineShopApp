package com.example.onlineapp.models;

import com.example.onlineapp.models.enums.ProductStatus;
import com.example.onlineapp.models.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private Long count;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "product_category",
//            joinColumns = {@JoinColumn(name = "product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
//    private List<ProductCategory> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;


    @Column
    private String image;

    @Column
    private LocalDateTime created;

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
