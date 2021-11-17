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
	public void insertWithEntityManager(Alumno alumno) throws Exception {
		if (alumno == null) {
			throw new Exception("No se ha enviado el alumno");
		}
		if (alumno.getNombre().equals(null) || alumno.getEmail().equals(null) || alumno.getDomicilio().equals(null)
				|| alumno.getDni().equals(null)) {
			throw new Exception("Los campos no pueden estar vacios");
		}
		entityManager.persist(alumno);
	}

	@Transactional
	public Alumno getAlumnoByDniWithEntityManager(String dni) throws Exception {
		if (dni.equals(null) || dni.equals("")) {
			throw new Exception("No se envio un DNI");
		}
		return entityManager.find(Alumno.class, dni);
	}

	@Transactional
	public Alumno getAlumnoByIdWithEntityManager(Long id) throws Exception {
		if (id.equals(null) || id.equals("")) {
			throw new Exception("No se envio un ID");
		}
		return entityManager.find(Alumno.class, id);
	}

	@Transactional
	public List<Alumno> findAllAlumnos() throws Exception {
		List<Alumno> alumnos = entityManager.createQuery("from Alumno").getResultList();
		if(alumnos.size()<=0) {
			throw new Exception("No hay alumnos registrados");
		}
		return alumnos;
	}

	@Transactional
	public List<Alumno> findByName(String name) throws Exception {
		List<Alumno> alumnos = entityManager.createQuery("from Alumno where nombre = :name").setParameter("name", name).getResultList();
		if(alumnos.size()<=0) {
			throw new Exception("No hay alumnos con el nombre");
		}
		return alumnos;
	}

	@Transactional
	public void updateAlumno(Alumno alumno) throws Exception {
		if(alumno == null) {
			throw new Exception("No hay alumno para actualizar");
		}
		entityManager.merge(alumno);
	}

	@Transactional
	public void removeAlumnoById(Long id) throws Exception {
		try {
			Alumno alumno = getAlumnoByIdWithEntityManager(id);
			entityManager.remove(alumno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void insertCursoInAlumno(Alumno alumno, List<Curso> curso) throws Exception {
		alumno = getAlumnoByIdWithEntityManager(alumno.getId_Alumno());
		Set<Curso> cursos = new HashSet<>();
		for (Curso c : cursos) {
			cursos.add(c);
			entityManager.merge(c);
		}
	}
}
