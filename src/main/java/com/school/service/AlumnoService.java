package com.school.service;

import java.util.List;

import com.school.generics.CrudActions;
import com.school.model.Alumno;
import com.school.model.Curso;
import com.school.model.RedSocial;

public interface AlumnoService extends CrudActions<Alumno , Long> {
	
	List<Curso> getCursosfromAlumno(Long idAlumno) throws Exception;
}
