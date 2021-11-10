package com.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.model.RedSocial;

@Repository
public class RedSocialRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void insertWithEntityManager(RedSocial redSocial) {
		entityManager.persist(redSocial);
	}

	@Transactional
	public RedSocial getRedSocialByIdWithEntityManager(Long id) {
		return entityManager.find(RedSocial.class, id);
	}

	@Transactional
	public List<RedSocial> findAllRedSocial() {
		return entityManager.createQuery("from RedSocial").getResultList();
	}

	@Transactional
	public List<RedSocial> findByName(String name) {
		return entityManager.createQuery("from RedSocial where nombre = :name").setParameter("name", name)
				.getResultList();
	}

	@Transactional
	public void updateRedSocial(RedSocial redSocial) {
		entityManager.merge(redSocial);
	}

	@Transactional
	public void removeProfesorById(Long id) {
		RedSocial redSocial = getRedSocialByIdWithEntityManager(id);
		try {
			entityManager.remove(redSocial);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
