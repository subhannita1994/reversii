package com.multiplayerGame.reversii.data.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.multiplayerGame.reversii.data.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{


}
