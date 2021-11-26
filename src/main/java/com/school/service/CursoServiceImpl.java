package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Curso;
import com.school.model.RedSocial;
import com.school.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void create(Curso curso) throws Exception {
		cursoRepository.insertWithEntityManager(curso);
	}

	@Override
	public Curso getById(Long id) throws Exception{
		return cursoRepository.getCursoByIdWithEntityManager(id);
	}

	@Override
	public List<Curso> getByName(String name) throws Exception {
		return cursoRepository.findByName(name);
	}

	@Override
	public List<Curso> getAll() throws Exception{
		return cursoRepository.findAllCursos();
	}

	@Override
	public void update(Curso curso) throws Exception {
		cursoRepository.updateCurso(curso);
	}

	@Override
	public void remove(Long id) throws Exception {
		cursoRepository.removeCursoById(id);
	}

}
