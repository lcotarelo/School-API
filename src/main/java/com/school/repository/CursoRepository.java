package com.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.school.model.Curso;

@Repository
public class CursoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void insertWithEntityManager(Curso curso) {
		entityManager.persist(curso);
	}

	@Transactional
	public Curso getCursoByIdWithEntityManager(Long id) {
		return entityManager.find(Curso.class, id);
	}

	@Transactional
	public List<Curso> findAllCursos() {
		return (List<Curso>) entityManager.createQuery("from Curso").getResultList();
	}

	@Transactional
	public List<Curso> findByName(String name) {
		return (List<Curso>) entityManager.createQuery("from Curso where nombre_curso = :name")
				.setParameter("name", name).getResultList();
	}

	@Transactional
	public void updateCurso(Curso curso) {
		entityManager.merge(curso);
	}

	@Transactional
	public void removeCursoById(Long id) {
		Curso curso = getCursoByIdWithEntityManager(id);
		try {
			entityManager.remove(curso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
