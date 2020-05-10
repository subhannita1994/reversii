package com.multiplayerGame.reversii.data.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.multiplayerGame.reversii.data.entity.Identifier;

@Repository
public interface IdentifierRepository extends CrudRepository<Identifier,String>, IdentifierRepositoryCustom{

	

}