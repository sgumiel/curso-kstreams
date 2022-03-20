package com.gumi.cursos.kstream.namesplitter.model.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dni {

	private Integer number;
	private String character;

	public String getCompleto(){
		return this.number+this.character;
	}
}
