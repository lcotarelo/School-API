package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.error.CustomError;
import com.school.model.Alumno;
import com.school.service.AlumnoServiceImpl;

@RestController
@RequestMapping(value = "/v1/api/alumnos")
public class AlumnoController {

	@Autowired
	AlumnoServiceImpl alumnoServiceImpl;

	// busqueda de todos los alumnos o por nombre con param
	@GetMapping
	public ResponseEntity<List<Alumno>> getAlumnos(@RequestParam(value = "name", required = false) String name)
			throws Exception {
		List<Alumno> alumnos = new ArrayList<>();
		if (name == null) {
			alumnos = alumnoServiceImpl.getAll();
			if (alumnos.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen alumnos"), HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
		} else {
			alumnos = alumnoServiceImpl.getByName(name);
			if (alumnos.size() >= 1) {
				return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existe el alumno solicitado"), HttpStatus.CONFLICT);
			}
		}
	}

	// Post
	@PostMapping
	public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno) throws Exception {
		alumnoServiceImpl.create(alumno);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
	}

	// Ver un registro con por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable(name = "id") Long idAlumno) throws Exception {
		Alumno alumno = alumnoServiceImpl.getById(idAlumno);
		if (alumno != null) {
			return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		} else {
			return new ResponseEntity("El usuario con id "+idAlumno+" no existe.", HttpStatus.NOT_FOUND);
		}
	}

	// Actualizar un registro
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Alumno> patchAlumno(@PathVariable(name = "id") Long idAlumno, @RequestBody Alumno alumno)
			throws Exception {
		Alumno alumnoAux = alumnoServiceImpl.getById(idAlumno);
		alumnoAux = Alumno.builder().nombre(alumno.getNombre()).dni(alumno.getDni()).domicilio(alumno.getDomicilio())
				.email(alumno.getEmail()).id_Alumno(idAlumno).build();
		alumnoServiceImpl.update(alumnoAux);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAlumno(@PathVariable(name = "id") Long idAlumno) throws Exception {
		alumnoServiceImpl.remove(idAlumno);
		return new ResponseEntity("Se ha eliminado correctamente el usuario "+idAlumno,HttpStatus.OK);
	}
}
