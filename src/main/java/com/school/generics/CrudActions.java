package com.school.generics;

import java.util.List;

import com.school.model.RedSocial;

public interface CrudActions<T , ID> {

	void create(T ob) throws Exception;
	
	T getById(ID id) throws Exception;
	
	List<T> getByName(String name) throws Exception;
	
	List<T> getAll() throws Exception;
	
	void update(T ob) throws Exception;
	
	void remove(ID id) throws Exception;

}
