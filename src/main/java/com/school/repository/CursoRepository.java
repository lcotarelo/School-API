package com.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.school.model.Alumno;
import com.school.model.Curso;

@Repository
public class CursoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void insertWithEntityManager(Curso curso) throws Exception {
		if (curso == null) {
			throw new Exception("No se ha enviado el alumno");
		}
		if (curso.getNombre_Curso().equals(null) || curso.getDescripcion().equals(null)) {
			throw new Exception("Los campos no pueden estar vacios");
		}
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
	public List<Curso> findByName(String name) throws Exception {
		List<Curso> cursos = entityManager.createQuery("from Curso where nombre_curso =:name")
				.setParameter("name", name).getResultList();
		if (cursos.size() <= 0) {
			throw new Exception("No hay cursos registrados");
		}
		return cursos;
	}

	@Transactional
	public void updateCurso(Curso curso) throws Exception {
		if (curso == null) {
			throw new Exception("No se encontro el curso");
		}
		entityManager.merge(curso);
	}

	@Transactional
	public void removeCursoById(Long id) throws Exception {
		Curso curso = getCursoByIdWithEntityManager(id);
		if (curso != null) {
			entityManager.remove(curso);
		} else {
			throw new Exception("Ocurrio un problema en el borrado del alumno");
		}
	}

}
