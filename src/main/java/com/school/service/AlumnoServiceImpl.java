package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Alumno;
import com.school.model.Curso;
import com.school.model.RedSocial;
import com.school.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public void create(Alumno alumno) throws Exception {
		alumnoRepository.insertWithEntityManager(alumno);
	}

	@Override
	public Alumno getById(Long id) throws Exception {
		return alumnoRepository.getAlumnoByIdWithEntityManager(id);
	}

	@Override
	public List<Alumno> getAll() throws Exception {
		return alumnoRepository.findAllAlumnos();
	}

	@Override
	public void remove(Long id) throws Exception {
		alumnoRepository.removeAlumnoById(id);
	}

	@Override
	public List<Alumno> getByName(String name) throws Exception {
		return alumnoRepository.findByName(name);
	}

	@Override
	public void update(Alumno alumno) throws Exception {
		alumnoRepository.updateAlumno(alumno);
	}

	@Override
	public List<RedSocial> getRedSocialByID(Long idRed) {
		return null;
	}

	@Override
	public void insertCursoInAlumno(Alumno alumno, List<Curso> cursos) throws Exception {
	}

}
