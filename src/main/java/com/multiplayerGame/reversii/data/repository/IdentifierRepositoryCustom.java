package com.multiplayerGame.reversii.data.repository;

import java.util.List;


public interface IdentifierRepositoryCustom {

	List<String> findTopFew(int numberOfPlayers);
}
