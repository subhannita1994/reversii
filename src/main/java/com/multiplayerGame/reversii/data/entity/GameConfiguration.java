package com.multiplayerGame.reversii.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GAME_CONFIGURATION")
public class GameConfiguration {

	@Id
	@Column(name="game_name")
	private String gameName;
	
	@Column(name="board_rows")
	private int boardRows;
	
	@Column(name="board_columns")
	private int boardCols;
	

	@Column(name="tile_size")
	private int tileSize;
	

	@Column(name="number_of_players")
	private int numberOfPlayers;
	

	@Column(name="starting_score")
	private int startingScore;


	public String getGameName() {
		return gameName;
	}


	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public int getBoardRows() {
		return boardRows;
	}


	public void setBoardRows(int boardRows) {
		this.boardRows = boardRows;
	}


	public int getBoardCols() {
		return boardCols;
	}


	public void setBoardCols(int boardCols) {
		this.boardCols = boardCols;
	}


	public int getTileSize() {
		return tileSize;
	}


	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}


	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}


	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}


	public int getStartingScore() {
		return startingScore;
	}


	public void setStartingScore(int startingScore) {
		this.startingScore = startingScore;
	}
	
	
}
