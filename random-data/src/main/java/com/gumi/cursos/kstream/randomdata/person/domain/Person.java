package com.gumi.cursos.kstream.randomdata.person.domain;

import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private Dni dni;
    private Name name;
    private Integer age;
}
