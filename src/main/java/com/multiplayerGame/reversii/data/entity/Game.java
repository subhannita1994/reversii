package com.multiplayerGame.reversii.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="GAME")
public class Game {

	@Id
	@Column(name="game_id")
	@SequenceGenerator(name= "GAME_SEQUENCE", sequenceName = "GAME_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="GAME_SEQUENCE")
	private int gameID;
	
	@Column(name="game_configuration")
	private String gameConfiguration;

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getGameConfiguration() {
		return gameConfiguration;
	}

	public void setGameConfiguration(String gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
	}
}
