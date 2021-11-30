package com.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "alumno_rrss")
public class AlumnoRRSS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id_nick;

	private String nickname;

	@ManyToOne
	@JsonBackReference(value="alumno")
	@JoinColumn(name = "id_Alumno")
	private Alumno alumno;

	@ManyToOne
	@JsonBackReference(value="redSocial")
	@JoinColumn(name = "id_RedSocial")
	private RedSocial redSocial;

	public String  getNombreRed() {
		return redSocial.getNombre();
	}
	
	public AlumnoRRSS(String nickname, Alumno alumno, RedSocial redSocial) {
		this.nickname = nickname;
		this.alumno = alumno;
		this.redSocial = redSocial;
	}

	


}
