package com.school.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
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
	
	@OneToMany(mappedBy = "redSocial")
	private List<AlumnoRRSS> listaRedes;

	@OneToMany(mappedBy = "profesor")
	private List<ProfesorRRSS> redesSocialProfesor;

}
