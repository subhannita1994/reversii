package com.multiplayerGame.reversii.business.domain;

import com.multiplayerGame.reversii.data.entity.GameConfiguration;

public class StartReversii {
	
	private int gameID;
	private int playerID1;
	private int playerID2;
	private CellValue[][] board;
	private GameConfiguration gameConfiguration;
	
	public StartReversii(GameConfiguration gameConfiguration, String gameName) {
		this.gameConfiguration = gameConfiguration;
		int rows = this.gameConfiguration.getBoardRows();	int cols = this.gameConfiguration.getBoardCols();
		this.board = new CellValue[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				this.board[i][j] = CellValue.EMPTY;
		board[3][3] = board[4][4] = CellValue.WHITE;
		board[4][3] = board[3][4] = CellValue.BLACK;
		board[2][4] = board[3][5] = board[4][2] = board[5][3] = CellValue.POSSIBLE;
	}
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getPlayerID1() {
		return playerID1;
	}
	public void setPlayerID1(int playerID1) {
		this.playerID1 = playerID1;
	}
	public int getPlayerID2() {
		return playerID2;
	}
	public void setPlayerID2(int playerID2) {
		this.playerID2 = playerID2;
	}
	public CellValue[][] getBoard(){
		return this.board;
	}

}
