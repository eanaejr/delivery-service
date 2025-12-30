package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code; // npr. barkod ili QR kod

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container; // lokacija spremnika

    @Column(nullable = false)
    private Boolean delivered = false;

    @Column
    private LocalDateTime arrivalTime; // vrijeme dolaska u skladište
}
