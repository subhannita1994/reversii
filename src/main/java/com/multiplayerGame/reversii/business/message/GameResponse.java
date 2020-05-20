package com.multiplayerGame.reversii.business.message;

import java.util.ArrayList;

import com.multiplayerGame.reversii.business.domain.Board;

public class GameResponse {
	
	private boolean success;
	private int gameID;
	private ArrayList<Board> rows;
	public GameResponse(boolean success, int gameID, ArrayList<Board> rows) {
		this.success = success;
		this.gameID = gameID;
		this.rows = rows;
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
	public ArrayList<Board> getRows() {
		return rows;
	}
	public void setRows(ArrayList<Board> rows) {
		this.rows = rows;
	}

}
