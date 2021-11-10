package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.RedSocialDAO;
import com.school.model.RedSocial;
import com.school.repository.RedSocialRepository;

@Service
public class RedSocialService implements RedSocialDAO{

	@Autowired
	private RedSocialRepository redSocialRepository;
	
	@Override
	public void create(RedSocial redSocial) throws Exception {
		redSocialRepository.insertWithEntityManager(redSocial);
	}

	@Override
	public RedSocial getById(Long id) throws Exception {
		return redSocialRepository.getRedSocialByIdWithEntityManager(id);
	}

	@Override
	public List<RedSocial> getByName(String name) {
		return redSocialRepository.findByName(name);
	}

	@Override
	public List<RedSocial> getAll() {
		return redSocialRepository.findAllRedSocial();
	}

	@Override
	public void update(RedSocial redSocial) {
		redSocialRepository.updateRedSocial(redSocial);
	}

	@Override
	public void remove(Long id) {
		redSocialRepository.removeProfesorById(id);
	}

}
