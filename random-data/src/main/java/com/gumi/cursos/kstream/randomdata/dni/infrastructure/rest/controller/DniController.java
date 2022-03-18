package com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.controller;

import com.gumi.cursos.kstream.randomdata.dni.application.service.DniService;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.dto.DniRestDTO;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.mapper.DniRestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("dni")
public class DniController {

    private final DniService dniService;
    private final DniRestMapper dniRestMapper;

    @PostMapping
    public ResponseEntity<DniRestDTO> createRandom() {

        log.debug("Create random dni");

        final var dni = this.dniService.createRandom();
        log.debug("Random dni: {}", dni);

        final var dniRestDto = this.dniRestMapper.toRestDTO(dni);
        log.debug("Dni mapped to dni rest dto: {}", dniRestDto);

        return ResponseEntity.ok(dniRestDto);
    }
}