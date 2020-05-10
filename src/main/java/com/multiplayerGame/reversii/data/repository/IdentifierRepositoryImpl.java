package com.multiplayerGame.reversii.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public class IdentifierRepositoryImpl implements IdentifierRepositoryCustom{

	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<String> findTopFew(int numberOfPlayers) {
		Query query = entityManager.createNativeQuery("SELECT identifier from IDENTIFIER LIMIT "+ numberOfPlayers);
		
        return  query.getResultList();
	}

}
