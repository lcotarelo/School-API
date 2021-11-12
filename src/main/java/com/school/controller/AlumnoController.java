package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.error.CustomError;
import com.school.model.Alumno;
import com.school.model.AlumnoRRSS;
import com.school.model.Curso;
import com.school.service.AlumnoService;

@RestController
@RequestMapping(value = "/v1")
public class AlumnoController {

	@Autowired
	AlumnoService alumnoService;

	// busqueda de todos los alumnos o por nombre con param
	@GetMapping(value = "/api/alumnos")
	public ResponseEntity<List<Alumno>> getAlumnos(@RequestParam(value = "name", required = false) String name) {

		List<Alumno> alumnos = new ArrayList<>();

		if (name == null) {
			alumnos = alumnoService.getAll();
			if (alumnos.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen alumnos"), HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
		} else {
			alumnos = alumnoService.getByName(name);
			if (alumnos.size() >= 1) {
				return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existe el alumno solicitado"), HttpStatus.OK);
			}
		}
	}

	// Post
	@PostMapping(value = "/api/alumnos",
			consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_ATOM_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<?> addAlumno(
			@RequestBody Alumno alumno) {
		try {
			System.err.println("Antes de crear");
			alumnoService.create(alumno);
			System.err.println("Creado");
			return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
		} catch (Exception e) {
			String mensaje = "Error: " + e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
		}
	}
	
	

	// Ver un registro con por ID
	@GetMapping(value = "/api/alumnos/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable(name = "id") Long idAlumno) {
		try {
			Alumno alumno = alumnoService.getById(idAlumno);
			return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error: " + e.getMessage() + e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
		}

	}

	// Actualizar un registro
	@PutMapping(value = "/api/alumnos/{id}")
	public ResponseEntity<Alumno> patchAlumno(@PathVariable(name = "id") Long idAlumno, @RequestBody Alumno alumno) {
		if (idAlumno == null || idAlumno <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un id de alumno valido"), HttpStatus.CONFLICT);
		}
		try {
			Alumno alumnoAux;
			alumnoAux = alumnoService.getById(idAlumno);

			if (alumnoAux == null) {
				return new ResponseEntity(new CustomError("No existe el alumno"), HttpStatus.CONFLICT);
			}
			alumnoAux = Alumno.builder().nombre(alumno.getNombre()).dni(alumno.getDni())
					.domicilio(alumno.getDomicilio()).email(alumno.getEmail()).id_Alumno(idAlumno).build();

			alumnoService.update(alumnoAux);

			return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error: " + e.getMessage() + e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
		}

	}

}
