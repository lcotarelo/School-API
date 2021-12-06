package com.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.school.model.AlumnoRRSS;
import com.school.model.Curso;

@Repository
public class AlumnoRRSSRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void insertWithEntityManager(AlumnoRRSS alumnoRRSS) throws Exception {
		if (alumnoRRSS == null) {
			throw new Exception("No se ha enviado el alumno");
		}
		entityManager.persist(alumnoRRSS);
	}
	
	@Transactional
	public List<AlumnoRRSS> findRedesFromAlumno(Long idAlumno) throws Exception {
		List<AlumnoRRSS> alumnoRRSS = entityManager.createNativeQuery(
				"SELECT ars.NICKNAME , rs.NOMBRE  FROM ALUMNO_RRSS ars \n"
				+ "inner join RED_SOCIAL rs  on ars.ID_RED_SOCIAL =  rs.ID_RED_SOCIAL \n"
				+ "inner join ALUMNO al on ars.ID_ALUMNO = al.ID_ALUMNO where ars.ID_ALUMNO  =:idAlumno")
				.setParameter("idAlumno", idAlumno).getResultList();
		return alumnoRRSS;
	}

}
