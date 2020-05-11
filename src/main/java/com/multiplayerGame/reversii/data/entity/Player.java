package com.multiplayerGame.reversii.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PLAYER")
public class Player {

	@Id
	@Column(name="player_id")
	@SequenceGenerator(name= "PLAYER_SEQUENCE", sequenceName = "PLAYER_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="PLAYER_SEQUENCE")
	private int playerID;
	
	@Column(name="player_name")
	private String playerName;
	
	@Column(name="identifier")
	private String identifier;
	
	@Column(name="score")
	private int score;
	
	@Column(name="player_type")
	private String playerType;
	
	@Column(name="game_id")
	private int game_id;
	
	

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	
	
}
