package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.school.model.Profesor;
import com.school.service.ProfesorService;

@RestController
@RequestMapping(value="/v1/api/profesores")
public class ProfesorController {

	@Autowired
	ProfesorService profesorService;

	// busqueda de todos los profesores o por nombre con param
	@GetMapping
	public ResponseEntity<List<Profesor>> getProfesores(
			@RequestParam(value = "name", required = false) String name) {

		List<Profesor> profesores = new ArrayList<>();

		if (name == null) {
			profesores = profesorService.getAll();
			if (profesores.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen profesores"), HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Profesor>>(profesores, HttpStatus.OK);
		} else {
			profesores = profesorService.getByName(name);
			if (profesores.size() >= 1) {
				return new ResponseEntity<List<Profesor>>(profesores, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existe el profesor solicitado"), HttpStatus.OK);
			}
		}
	}

	// Post
	@PostMapping
	public ResponseEntity<Profesor> addProfesor(@RequestBody Profesor profesor, UriComponentsBuilder uriComponentsBuilder) {
		try {
			profesorService.create(profesor);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					uriComponentsBuilder.path("/v1/api/profesores/{id}").buildAndExpand(profesor.getId_Profesor()).toUri());
			return new ResponseEntity<Profesor>(profesor, HttpStatus.CREATED);
		} catch (Exception e) {
			String mensaje = "Error: " + e.getMessage() + e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
		}
	}

	// Ver un registro con por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Profesor> getProfesorById(@PathVariable(name = "id") Long idProfesor)  {
	try {
		Profesor profesor = profesorService.getById(idProfesor);
		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}
	catch(Exception e) {
		String mensaje = "Error: " + e.getMessage() + e.getMessage();
		return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
	}

	}

	// Actualizar un registro
	@PutMapping(value = "/{id}")
	public ResponseEntity<Profesor> patchProfesor(@PathVariable(name = "id") Long idProfesor, @RequestBody Profesor profesor) {
		if (idProfesor == null || idProfesor <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un id de profesor valido"), HttpStatus.CONFLICT);
		}
		try {
			Profesor profesorAux;
			profesorAux = profesorService.getById(idProfesor);
			
			if (profesorAux == null) {
				return new ResponseEntity(new CustomError("No existe el profesor"), HttpStatus.CONFLICT);
			}
			profesorAux = Profesor.builder().nombre(profesor.getNombre()).dni(profesor.getDni()).domicilio(profesor.getDomicilio())
					.email(profesor.getEmail()).id_Profesor(idProfesor).build();
			
			profesorService.update(profesorAux);
			
			return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error: " + e.getMessage() + e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.CONFLICT);
		}

	}

}
