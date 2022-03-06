package com.gumi.cursos.kstream.randomdata.dni;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Dni implements Serializable {

    private Integer number;
    private String character;

    public String getCompleto(){
        return this.number+this.character;
    }

}
