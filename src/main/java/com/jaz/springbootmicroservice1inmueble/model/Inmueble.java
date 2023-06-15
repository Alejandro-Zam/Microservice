package com.jaz.springbootmicroservice1inmueble.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inmueble")
@Data
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;

    @Column(name = "foto", length = 1000, nullable = true)
    private String pricture;

    @Column(name = "precio", nullable = false)
    private double precion;

    @Column(name = "fecha_Creacion", nullable = false)
    private LocalDateTime fecha_Creacion;
}
