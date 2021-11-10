package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.ProfesorDAO;
import com.school.model.Alumno;
import com.school.model.Profesor;
import com.school.model.RedSocial;
import com.school.repository.ProfesorRepository;

@Service
public class ProfesorService implements ProfesorDAO {

	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Override
	public void create(Profesor profesor) {
		profesorRepository.insertWithEntityManager(profesor);
	}

	@Override
	public Profesor getById(Long id) {
		return profesorRepository.getProfesorByIdWithEntityManager(id);
	}

	@Override
	public List<Profesor> getByName(String name) {
		return (List<Profesor>) profesorRepository.findByName(name);

	}

	@Override
	public List<Profesor> getAll() {
		return  profesorRepository.findAllProfesor();
	}

	@Override
	public void update(Profesor profesor) {
		profesorRepository.updateProfesor(profesor);
	}

	@Override
	public void remove(Long id) {
		profesorRepository.removeProfesorById(id);
	}

	@Override
	public List<RedSocial> getRedSocialByID(Long idRed) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
