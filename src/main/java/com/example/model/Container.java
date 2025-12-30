package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "containers")
@Data               // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Default constructor required by JPA
@AllArgsConstructor // Constructor with all fields
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Double lat;

    @Column
    private Double lng;
}
