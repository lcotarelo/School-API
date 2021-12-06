package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.AlumnoRRSS;
import com.school.repository.AlumnoRRSSRepository;

@Service
public class AlumnoRRSSServiceImpl {

	@Autowired
	private AlumnoRRSSRepository alumnoRRSSRepository;
	
	public void create(AlumnoRRSS alumnoRRSS) throws Exception {
		alumnoRRSSRepository.insertWithEntityManager(alumnoRRSS);
	}
	
	public List<AlumnoRRSS> getRedesfromAlumno(Long idAlumno) throws Exception{
		return alumnoRRSSRepository.findRedesFromAlumno(idAlumno);

	}
}
