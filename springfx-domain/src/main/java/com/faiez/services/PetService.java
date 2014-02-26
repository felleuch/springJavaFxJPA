package com.faiez.services;

import com.faiez.exception.BusinessException;
import com.faiez.domain.Pet;

import java.util.List;

/**
 * Created by faiez.elleuch on 19/02/14.
 */
public interface PetService {

	public Pet create(Pet pet);
	public Pet delete(int id) throws BusinessException;
	public List<Pet> findAll();
	public Pet update(Pet pet) throws BusinessException;
	public Pet findById(int id);
}
