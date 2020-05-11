package com.multiplayerGame.reversii.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_player_id", referencedColumnName = "player_id")
	private Player currentPlayerID;

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
	
	public Player getCurrentPlayerID() {
		return this.currentPlayerID;
	}
	
	public void setCurrentPlayerID(Player currentPlayerID) {
		this.currentPlayerID = currentPlayerID;
	}
	
}
