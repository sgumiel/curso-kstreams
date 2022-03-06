package com.gumi.cursos.kstream.randomdata.person.domain;

import com.gumi.cursos.kstream.randomdata.dni.Dni;
import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable {

    private Dni dni;
    private Name name;
    private Integer age;
}
