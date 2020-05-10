package com.multiplayerGame.reversii.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.multiplayerGame.reversii.data.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer>{

}
