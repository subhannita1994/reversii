package com.multiplayerGame.reversii.business.message;

import java.util.ArrayList;

import com.multiplayerGame.reversii.business.domain.Board;

public class GameResponse {
	
	private boolean success;
	private int gameID;
	String[][] board;
	public GameResponse(boolean success, int gameID, String[][] board) {
		this.success = success;
		this.gameID = gameID;
		this.board = board;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String[][] getBoard(){
		return this.board;
	}
	public void setBoard(String[][] board) {
		this.board = board;
	}

}
