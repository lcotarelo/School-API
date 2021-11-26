package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.error.CustomError;
import com.school.model.Curso;
import com.school.service.CursoServiceImpl;

@RestController
@RequestMapping(value = "/v1/api/cursos")
public class CursoController {

	@Autowired
	CursoServiceImpl cursoServiceImpl;

	// busqueda de todos los cursos o por nombre con param
	@GetMapping
	public ResponseEntity<List<Curso>> getAllCursos(@RequestParam(value = "name", required = false) String name)
			throws Exception {
		List<Curso> cursos = new ArrayList<>();

		if (name == null) {
			cursos = cursoServiceImpl.getAll();
			if (cursos.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen cursos"), HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
		} else {
			cursos = cursoServiceImpl.getByName(name);
			if (cursos.size() >= 1) {
				return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existen cursos"), HttpStatus.CONFLICT);
			}
		}
	}

	// Ver un curso con por ID
	@GetMapping("/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable(value = "id") Long id_Curso) throws Exception {

		if (id_Curso == null || id_Curso <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un ID valido"), HttpStatus.NOT_FOUND);
		}
		if (cursoServiceImpl.getById(id_Curso) == null) {
			return new ResponseEntity(new CustomError("No se encuentra el curso con ese ID"), HttpStatus.NOT_FOUND);
		}
		Curso curso = cursoServiceImpl.getById(id_Curso);
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	// Insertar curso nuevo
	@PostMapping
	public ResponseEntity<Curso> addCurso(@RequestBody Curso curso, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		cursoServiceImpl .create(curso);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/api/cursos/{id}").buildAndExpand(curso.getId_Curso()).toUri());
		return new ResponseEntity<Curso>(curso, HttpStatus.CREATED);

	}

	//update curso
	@PatchMapping(value="/{id}")
	public ResponseEntity<Curso> updateCurso(@PathVariable(value="id") Long id_Curso, @RequestBody Curso curso) throws Exception{
		Curso cursoAux = cursoServiceImpl.getById(id_Curso);
		cursoAux= Curso.builder()
				.nombre_Curso(curso.getNombre_Curso())
				.descripcion(curso.getDescripcion())
				.id_Curso(id_Curso)
				.build();
		cursoServiceImpl.update(cursoAux);
		return new ResponseEntity<Curso>(curso,HttpStatus.OK);
	}
}







