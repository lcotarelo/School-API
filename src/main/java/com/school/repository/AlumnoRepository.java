package com.school.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.school.model.Alumno;
import com.school.model.Curso;

@Repository
public class AlumnoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void insertWithEntityManager(Alumno alumno) {
		entityManager.persist(alumno);
	}

	@Transactional
	public Alumno getAlumnoByDniWithEntityManager(String dni) {
		return entityManager.find(Alumno.class, dni);
	}

	@Transactional
	public Alumno getAlumnoByIdWithEntityManager(Long id) {
		return entityManager.find(Alumno.class, id);
	}

	@Transactional
	public List<Alumno> findAllAlumnos() {
		return (List<Alumno>) entityManager.createQuery("from Alumno").getResultList();
	}

	@Transactional
	public List<Alumno> findByName(String name) {
		return entityManager.createQuery("from Alumno where nombre = :name").setParameter("name", name).getResultList();
	}

	@Transactional
	public void updateAlumno(Alumno alumno) {
		entityManager.merge(alumno);
	}

	@Transactional
	public void removeAlumnoById(Long id) {
		Alumno alumno = getAlumnoByIdWithEntityManager(id);
		try {
			entityManager.remove(alumno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void insertCursoInAlumno(Alumno alumno, List<Curso> curso) {
		alumno = getAlumnoByIdWithEntityManager(alumno.getId_Alumno());
		Set<Curso> cursos = new HashSet<>();
		for (Curso c : cursos) {
			cursos.add(c);
			entityManager.merge(c);
			}
	}
	}
