package com.gumi.cursos.kstream.randomdata.dni.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dni {

    private Integer number;
    private String character;

    public String getCompleto(){
        return this.number+this.character;
    }
}
