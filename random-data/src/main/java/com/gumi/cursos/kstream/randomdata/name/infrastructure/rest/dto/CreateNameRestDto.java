package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNameRestDto implements Serializable {

    @NotNull(message = "El campo name es obligatorio")
    private String name;

    @NotNull(message = "El campo isCompuesto es obligatorio")
    private Boolean isCompuesto;
}
