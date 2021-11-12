package com.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "alumno_rrss")
public class AlumnoRRSS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_nick;

	private String nickname;

	@ManyToOne
	@JsonBackReference//Indico que es la referencia de retorno(evitando ciclo infinito)
	@JoinColumn(name = "id_Alumno", insertable = true, nullable = false, updatable = false)
	private Alumno alumno;

	@ManyToOne
	@JsonBackReference// Indico que es la referencia de retorno(evitando ciclo infinito)
	@JoinColumn(name = "id_RedSocial", insertable = true, nullable = false, updatable = false)
	private RedSocial redSocial;
	


}
