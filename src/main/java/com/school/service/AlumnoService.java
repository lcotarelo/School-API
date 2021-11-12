package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.AlumnoDAO;
import com.school.model.Alumno;
import com.school.model.Curso;
import com.school.model.RedSocial;
import com.school.repository.AlumnoRepository;

@Service
public class AlumnoService implements AlumnoDAO {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public void create(Alumno alumno) throws Exception {
		if(alumno == null) {
			throw new Exception("No se ha enviado el alumno");
		}
		if (alumno.getNombre().equals(null) || alumno.getEmail().equals(null) || alumno.getDomicilio().equals(null)
				|| alumno.getDni().equals(null)) {
			throw new Exception("Los campos no pueden estar vacios");
		}
		alumnoRepository.insertWithEntityManager(alumno);
	}

	@Override
	public Alumno getById(Long id) throws Exception {
		Alumno alumno = alumnoRepository.getAlumnoByIdWithEntityManager(id);
		if (alumno == null || alumno.getId_Alumno() <= 0) {
			throw new Exception("ID fuera de rango");
		}
		return alumno;
	}

	@Override
	public List<Alumno> getAll() {
		return (List<Alumno>) alumnoRepository.findAllAlumnos();
	}

	@Override
	public void remove(Long id) {
		alumnoRepository.removeAlumnoById(id);
	}

	@Override
	public List<Alumno> getByName(String name) {
		return (List<Alumno>) alumnoRepository.findByName(name);
	}

	@Override
	public void update(Alumno alumno) {
		alumnoRepository.updateAlumno(alumno);
	}

	@Override
	public List<RedSocial> getRedSocialByID(Long idRed) {
		return null;
	}

	@Override
	public void insertCursoInAlumno(Alumno alumno, List<Curso> cursos) {
		alumnoRepository.insertCursoInAlumno(alumno, cursos);
	}

}
