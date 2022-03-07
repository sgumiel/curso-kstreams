package com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.dto;

import com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.dto.DniRestDTO;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto.NameRestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRestDTO implements Serializable {

    private NameRestDTO name;
    private DniRestDTO dni;
    private Integer age;
}
