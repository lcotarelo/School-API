package com.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.school.model.Profesor;

@Repository
public class ProfesorRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void insertWithEntityManager(Profesor profesor) {
		entityManager.persist(profesor);
	}

	@Transactional
	public Profesor getProfesorByDniWithEntityManager(String dni) {
		return entityManager.find(Profesor.class, dni);
	}

	@Transactional
	public Profesor getProfesorByIdWithEntityManager(Long id) {
		return entityManager.find(Profesor.class, id);
	}

	@Transactional
	public List<Profesor> findAllProfesor() {
		return entityManager.createQuery("from Profesor").getResultList();
	}

	@Transactional
	public List<Profesor> findByName(String name) {
		return entityManager.createQuery("from Profesor where nombre = :name").setParameter("name", name).getResultList();
	}

	@Transactional
	public void updateProfesor(Profesor profesor) {
		entityManager.merge(profesor);
	}

	@Transactional
	public void removeProfesorById(Long id) {
		Profesor profesor = getProfesorByIdWithEntityManager(id);
		try {
			entityManager.remove(profesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
