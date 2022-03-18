package com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "dni")
@AllArgsConstructor
@NoArgsConstructor
public class DniEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private Integer number;

    @Column(name = "character")
    private String character;

    @Column(name = "used", insertable = false)
    private Boolean used;

}
