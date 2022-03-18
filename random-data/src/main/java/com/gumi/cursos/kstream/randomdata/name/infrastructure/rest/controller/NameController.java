package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.controller;

import com.gumi.cursos.kstream.randomdata.name.application.service.NameService;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto.CreateNameRestDto;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto.NameRestDTO;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.mapper.NameRestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/name")
public class NameController {

    private final NameService nameService;
    private final NameRestMapper nameRestMapper;

    @GetMapping
    public ResponseEntity<List<NameRestDTO>> findAll(){

        log.debug("Find all name");

        final var names = (List<Name>)this.nameService.findAll();
        log.debug("Names found: {}", names.size());

        final var nameRestDtoList = names.stream()
                .map(this.nameRestMapper::toResponse).toList();
        log.debug("Names mapped to names rest dto");

        return ResponseEntity.ok(nameRestDtoList);
    }

    @GetMapping("random")
    public ResponseEntity<NameRestDTO> getRandomName() {

        log.debug("Get random name");

        final var name = this.nameService.findRandom();
        log.debug("Random name: {}", name);

        final var nameRestDto = this.nameRestMapper.toResponse(name);
        log.debug("Name mapped to rest dto");

        return ResponseEntity.ok(nameRestDto);

    }
    @PostMapping
    public ResponseEntity<NameRestDTO> createName(@RequestBody @Validated CreateNameRestDto createNameRestDto){

        log.debug("Create name request: {}", createNameRestDto);

        final var name = this.nameRestMapper.toDomain(createNameRestDto);
        log.debug("Create name request mapped to domain");

        final var nameCreated = this.nameService.save(name);
        log.debug("Name created: {}", nameCreated);

        final var nameRestDto = this.nameRestMapper.toResponse(nameCreated);
        log.debug("Name created mapped to rest dto");

        return ResponseEntity.ok(nameRestDto);

    }
}
