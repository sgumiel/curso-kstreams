package com.gumi.cursos.kstream.randomdata.purcharse.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.gumi.cursos.kstream.randomdata.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Purchase implements Serializable {

    private String personDNI;
    private List<Item> items;
    private OffsetDateTime date;
    private Double total;

}
