package com.school.service;

import java.util.List;

import com.school.generics.CrudActions;
import com.school.model.Profesor;
import com.school.model.RedSocial;

public interface ProfesorService extends CrudActions<Profesor, Long> {

	public List<RedSocial> getRedSocialByID(Long idRed);

}
