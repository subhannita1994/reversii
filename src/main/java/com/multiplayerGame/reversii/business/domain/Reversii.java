package com.multiplayerGame.reversii.business.domain;

import com.multiplayerGame.reversii.data.entity.GameConfiguration;

public class Reversii {
	
	private int gameID;
	private int playerID1;
	private int playerID2;
	private int playerScore1;
	private int playerScore2;
	private CellValue[][] board;
	private GameConfiguration gameConfiguration;
	private int currentPllayerID;
	
	public Reversii(GameConfiguration gameConfiguration, String gameName) {
		this.gameConfiguration = gameConfiguration;
		int rows = this.gameConfiguration.getBoardRows();	int cols = this.gameConfiguration.getBoardCols();
		this.board = new CellValue[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				this.board[i][j] = CellValue.EMPTY;
		board[3][3] = board[4][4] = CellValue.WHITE;
		board[4][3] = board[3][4] = CellValue.BLACK;
		board[2][4] = board[3][5] = board[4][2] = board[5][3] = CellValue.POSSIBLE;
		this.playerScore1 = this.playerScore2 = 2;
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

	public void setCurrentPlayer(int playerID) {
		this.currentPllayerID = playerID;
		
	}
	public int getCurrentPlayer() {
		return this.currentPllayerID;
	}
	
	public int getPlayerScore1() {
		return this.playerScore1;
	}
	
	public int getPlayerScore2() {
		return this.playerScore2;
	}
	
	public void move(int row, int col) {
		if(board[row][col].equals(CellValue.POSSIBLE)) {
			System.out.println("clicked on possible:"+row+col);
			//change the board
			
			//update score
			
			//swap players
			if(this.currentPllayerID == this.playerID1)
				this.currentPllayerID = this.playerID2;
			else
				this.currentPllayerID = this.playerID1;
		}
			
	}

}
