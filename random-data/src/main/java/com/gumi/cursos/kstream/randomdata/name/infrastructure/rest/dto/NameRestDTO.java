package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameRestDTO implements Serializable {

    private String name;
    private Boolean isCompuesto;
}
