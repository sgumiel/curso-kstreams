package com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "name")
public class NameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "compuesto", columnDefinition = "boolean default false")
    private Boolean isCompuesto;

}