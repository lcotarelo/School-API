package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.school.error.CustomError;
import com.school.model.Alumno;
import com.school.model.AlumnoRRSS;
import com.school.model.Curso;
import com.school.model.RedSocial;
import com.school.service.AlumnoRRSSServiceImpl;
import com.school.service.AlumnoServiceImpl;
import com.school.service.CursoServiceImpl;
import com.school.service.RedSocialServiceImpl;

@RestController
@RequestMapping(value = "/v1/api/alumnos")
public class AlumnoController {

	@Autowired
	AlumnoServiceImpl alumnoServiceImpl;

	@Autowired
	CursoServiceImpl cursoServiceImpl;

	@Autowired
	RedSocialServiceImpl redSocialServiceImpl;

	@Autowired
	AlumnoRRSSServiceImpl alumnoRRSSServiceImpl;

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

	// Get by DNI
	@GetMapping(value = "/")
	public ResponseEntity<Alumno> getByDni(@RequestParam(value = "dni", required = false) String dni) throws Exception {
		Alumno alumno = null;
		try {
			alumno = alumnoServiceImpl.getByDni(dni);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	//Get Cursos de alumno
	@GetMapping(value = "/{idAlumno}/cursos/")
	public ResponseEntity<?> getCursoInAlumno(@PathVariable(value = "idAlumno") Long idAlumno) throws Exception {

		if (idAlumno == null) {
			return new ResponseEntity("No se ingreso un id", HttpStatus.NOT_FOUND);
		}
		if (alumnoServiceImpl.getById(idAlumno) == null) {
			return new ResponseEntity("No existe el alumno", HttpStatus.NOT_FOUND);
		}
		if (alumnoServiceImpl.getById(idAlumno) != null) {
			List<Curso> cursos = alumnoServiceImpl.getCursosfromAlumno(idAlumno);
			return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
		}
		return null;
	}

	// Insercion de nuevo alumno
	@PostMapping
	public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		alumnoServiceImpl.create(alumno);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/api/alumnos/{id}").buildAndExpand(alumno.getId_Alumno()).toUri());
		return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
	}

	// Ver un alumno con por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable(value = "id") Long idAlumno) throws Exception {
		Alumno alumno = alumnoServiceImpl.getById(idAlumno);
		if (alumno == null) {
			return new ResponseEntity("El usuario con id " + idAlumno + " no existe.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	// Actualizar un alumno
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Alumno> patchAlumno(@PathVariable(value = "id") Long idAlumno, @RequestBody Alumno alumno)
			throws Exception {
		Alumno alumnoAux = alumnoServiceImpl.getById(idAlumno);
		alumnoAux = Alumno.builder().nombre(alumno.getNombre()).dni(alumno.getDni()).domicilio(alumno.getDomicilio())
				.email(alumno.getEmail()).id_Alumno(idAlumno).build();
		alumnoServiceImpl.update(alumnoAux);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	// Agregar RedSocial en alumno
	@PostMapping(value = "/{idAlumno}/rrss/{idRRSS}")
	public ResponseEntity<Alumno> addRRSSintoAlumno(@RequestParam(value = "nickname", required = false) String nickname,
			@PathVariable(value = "idAlumno") Long idAlumno, @PathVariable(value = "idRRSS") Long idRRSS)
			throws Exception {
		Alumno alumno = alumnoServiceImpl.getById(idAlumno);
		RedSocial rrss = redSocialServiceImpl.getById(idRRSS);
		List<AlumnoRRSS> redes = alumno.getRedesSocialAlumno();

		AlumnoRRSS alumnoRRSS = AlumnoRRSS.builder().alumno(alumno).nickname(nickname).redSocial(rrss).build();
		alumnoRRSSServiceImpl.create(alumnoRRSS);
		redes.add(alumnoRRSS);
		alumno.setRedesSocialAlumno(redes);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	//Get Cursos de alumno
	@GetMapping(value = "/{idAlumno}/rrss/")
	public ResponseEntity<List<AlumnoRRSS>> getRRSSInAlumno(@PathVariable(value = "idAlumno") Long idAlumno) throws Exception {

		if (idAlumno == null) {
			return new ResponseEntity("No se ingreso un id", HttpStatus.NOT_FOUND);
		}
		if (alumnoServiceImpl.getById(idAlumno) == null) {
			return new ResponseEntity("No existe el alumno", HttpStatus.NOT_FOUND);
		}
		if (alumnoServiceImpl.getById(idAlumno) != null) {
			List<AlumnoRRSS> alumnoRRSS = alumnoRRSSServiceImpl.getRedesfromAlumno(idAlumno);
			if(alumnoRRSS.isEmpty()) {
				return new ResponseEntity("No se encuentra ninguna red social asociada al alumno de id "+idAlumno, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<AlumnoRRSS>>(alumnoRRSS, HttpStatus.OK);
		}
		return null;
	}

	// Agregar curso en alumno
	@PostMapping(value = "/{idAlumno}/cursos/{idCurso}")
	public ResponseEntity<Alumno> addCursoIntoAlumno(@PathVariable(value = "idAlumno") Long idAlumno,
			@PathVariable(value = "idCurso") Long idCurso) throws Exception {
		Alumno alumno = alumnoServiceImpl.getById(idAlumno);
		Curso curso = cursoServiceImpl.getById(idCurso);
		if (curso == null) {
			return new ResponseEntity("El id del curso ingresado no existe", HttpStatus.NOT_FOUND);
		}
		List<Curso> cursos = alumno.getCursos();
		cursos.add(curso);
		alumno.setCursos(cursos);
		alumnoServiceImpl.update(alumno);
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

	// Eliminar un alumno
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAlumno(@PathVariable(value = "id") Long idAlumno) throws Exception {
		alumnoServiceImpl.remove(idAlumno);
		return new ResponseEntity("Se ha eliminado correctamente el usuario " + idAlumno, HttpStatus.OK);
	}

}
