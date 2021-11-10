package com.school.generics;

import java.util.List;

import com.school.model.RedSocial;

public interface CrudActions<T , ID> {

	void create(T ob) throws Exception;
	
	T getById(ID id) throws Exception;
	
	List<T> getByName(String name);
	
	List<T> getAll();
	
	void update(T ob);
	
	void remove(ID id);

}
