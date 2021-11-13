package com.school.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Curso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Curso;

	private String nombre_Curso;

	private String descripcion;

	@JsonBackReference(value="alumno_curso")
	@ManyToMany(mappedBy = "cursos")
	private Set<Alumno> alumnos;

	@JsonBackReference(value="profesor_curso")
	@ManyToMany(mappedBy = "cursodictado")
	private Set<Profesor> profesores;

}
