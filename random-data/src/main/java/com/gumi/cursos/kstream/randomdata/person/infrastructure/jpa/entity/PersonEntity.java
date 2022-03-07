package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameid")
    private Long nameId;

    @Column(name = "dniid")
    private Long dniId;

    @Column(name = "age")
    private Integer age;
}
