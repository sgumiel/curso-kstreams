package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity;

import java.io.Serializable;

import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.entity.DniEntity;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity.NameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class PersonAggregateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "nameid")
    @OneToOne
    private NameEntity name;

    @JoinColumn(name = "dniid")
    @OneToOne
    private DniEntity dni;

    @Column(name = "age")
    private Integer age;
}
