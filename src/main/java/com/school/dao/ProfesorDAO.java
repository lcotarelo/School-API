package com.school.dao;

import java.util.List;

import com.school.generics.CrudActions;
import com.school.model.Profesor;
import com.school.model.RedSocial;

public interface ProfesorDAO extends CrudActions<Profesor, Long> {

	public List<RedSocial> getRedSocialByID(Long idRed);

}
