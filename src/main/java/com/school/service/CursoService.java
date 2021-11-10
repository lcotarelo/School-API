package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.CursoDAO;
import com.school.model.Curso;
import com.school.model.RedSocial;
import com.school.repository.CursoRepository;

@Service
public class CursoService implements CursoDAO {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void create(Curso curso) {
		cursoRepository.insertWithEntityManager(curso);
	}

	@Override
	public Curso getById(Long id) {
		return cursoRepository.getCursoByIdWithEntityManager(id);
	}

	@Override
	public List<Curso> getByName(String name) {
		return cursoRepository.findByName(name);
	}

	@Override
	public List<Curso> getAll() {
		return cursoRepository.findAllCursos();
	}

	@Override
	public void update(Curso curso) {
		cursoRepository.updateCurso(curso);
	}

	@Override
	public void remove(Long id) {
		cursoRepository.removeCursoById(id);
	}

}
