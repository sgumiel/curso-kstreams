package com.gumi.cursos.kstream.randomdata.item.domain;

public enum TaxType {

    SUPER_REDUCED(1, 4.0),
    REDUCED(2, 7.0),
    NORMAL(3, 21.0);

    private Integer type;
    private Double percentage;

    TaxType(Integer type, Double percentage) {
        this.type = type;
        this.percentage = percentage;
    }
}
