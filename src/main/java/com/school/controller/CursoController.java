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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.error.CustomError;
import com.school.model.Alumno;
import com.school.model.Curso;
import com.school.repository.CursoRepository;
import com.school.service.CursoService;

@RestController
@RequestMapping(value = "/v1/api/cursos")
public class CursoController {

	@Autowired
	CursoService cursoService;

	@GetMapping
	public ResponseEntity<List<Curso>> getAllCursos(@RequestParam(value = "name", required = false) String name) {
		List<Curso> cursos = new ArrayList<>();

		if (name == null) {
			cursos = cursoService.getAll();
			if (cursos.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen cursos"), HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
			}
		} else {
			cursos = cursoService.getByName(name);
			if (cursos.size() >= 1) {
				return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existen cursos"), HttpStatus.CONFLICT);
			}
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable(value = "id") Long id_Curso) {

		if (id_Curso == null || id_Curso <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un ID valido"), HttpStatus.NOT_FOUND);
		}
		if (cursoService.getById(id_Curso) == null) {
			return new ResponseEntity(new CustomError("No se encuentra el curso con ese ID"), HttpStatus.NOT_FOUND);
		}
		Curso curso = cursoService.getById(id_Curso);
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Curso> addCurso(@RequestBody Curso curso,	UriComponentsBuilder uriComponentsBuilder) {
		
		if(curso.getNombre_Curso()==null){
			return new ResponseEntity(new CustomError("Error: Campo nombre vacio"), HttpStatus.CONFLICT);
		}
		if(curso.getNombre_Curso().equals(cursoService.getByName(curso.getNombre_Curso()))){
			return new ResponseEntity(new CustomError("Error: Este curso ya existe"), HttpStatus.CONFLICT);
		}
		
		cursoService.create(curso);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/alumnos/{id}").buildAndExpand(curso.getId_Curso()).toUri());
		return new ResponseEntity<Curso>(curso, HttpStatus.CREATED);
		
	}
}

