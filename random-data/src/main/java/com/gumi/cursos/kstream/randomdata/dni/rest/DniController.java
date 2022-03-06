package com.gumi.cursos.kstream.randomdata.dni.rest;

import com.gumi.cursos.kstream.randomdata.dni.Dni;
import com.gumi.cursos.kstream.randomdata.dni.service.DniService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("dni")
public class DniController {

    private final DniService dniService;

    @GetMapping("random")
    public ResponseEntity<Dni> getRandom() {

        final var dni = this.dniService.createRandom();

        return ResponseEntity.ok(dni);


    }
}
