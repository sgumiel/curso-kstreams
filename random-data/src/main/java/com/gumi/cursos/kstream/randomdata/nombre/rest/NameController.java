package com.gumi.cursos.kstream.randomdata.nombre.rest;

import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;
import com.gumi.cursos.kstream.randomdata.nombre.service.NameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/name")
public class NameController {

    private final NameService nameService;

    @GetMapping
    public ResponseEntity<List<String>> findall(){
        final var names = (List<Name>)this.nameService.findAll();
        return ResponseEntity.ok(
                names.stream()
                .map(Name::getName)
                .toList()
        );

    }

    @GetMapping("random")
    public ResponseEntity<Name> getRandomName() {

        return ResponseEntity.ok(this.nameService.findRandomName());

    }
    @PostMapping
    public ResponseEntity<String> createName(@RequestBody Name name){

        final var nameSaved = this.nameService.create(name);
        return ResponseEntity.ok(name.getName());

    }
}
