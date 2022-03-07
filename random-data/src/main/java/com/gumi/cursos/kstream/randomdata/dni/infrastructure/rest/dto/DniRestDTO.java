package com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DniRestDTO implements Serializable {

    private Integer number;
    private String character;
}
