package com.school.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class RedSocial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_RedSocial;

	private String nombre;

	private String icono;
	
	@JsonBackReference
	@OneToOne(mappedBy = "redSocial")
	private AlumnoRRSS alumnoRRSS;

	@JsonBackReference
	@OneToOne(mappedBy = "redSocial")
	private ProfesorRRSS profesorRRSS;

}
