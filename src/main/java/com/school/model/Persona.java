package com.school.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona implements Serializable{

	private String nombre;
	private String email;
	private String dni;
	private String domicilio;
		

}
