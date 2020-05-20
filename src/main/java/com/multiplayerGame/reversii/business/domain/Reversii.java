package com.multiplayerGame.reversii.business.domain;

import java.util.ArrayList;

import com.multiplayerGame.reversii.data.entity.GameConfiguration;

public class Reversii {
	
	private int gameID;
	private int playerID1;
	private int playerID2;
	private int playerScore1;
	private int playerScore2;
	private String playerName1;
	private String playerName2;
	private CellValue playerIdentifier1;
	private CellValue playerIdentifier2;
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
		board[2][3] = board[3][2] = board[4][5] = board[5][4] = CellValue.POSSIBLE;
		this.playerScore1 = this.playerScore2 = 2;
	}
	public void setPlayerName1(String playerName) {
		this.playerName1 = playerName;
	}
	public void setPlayerName2(String playerName) {
		this.playerName2 = playerName;
	}
	public String getPlayerName1() {
		return this.playerName1;
	}
	public String getPlayerName2() {
		return this.playerName2;
	}
	public void setPlayerIdentifier1(String identifier) {
		if(identifier.equals("BLACK"))
			playerIdentifier1 = CellValue.BLACK;
		else
			playerIdentifier1 = CellValue.WHITE;
	}
	public void setPlayerIdentifier2(String identifier) {
		if(identifier.equals("BLACK"))
			playerIdentifier2 = CellValue.BLACK;
		else
			playerIdentifier2 = CellValue.WHITE;
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
	public int getPlayerIDByName(String playerName) {
		if(this.playerName1.equals(playerName))
			return this.playerID1;
		else
			return this.playerID2;
	}
	
	public void  move(int rowIndex, int colIndex) {
			//change the board
			CellValue curPlayerCellValue = (this.currentPllayerID == playerID1) ? this.playerIdentifier1 : this.playerIdentifier2;
			CellValue oppoPlayerCellValue = (this.currentPllayerID == playerID1) ? this.playerIdentifier2 : this.playerIdentifier1;
			board[rowIndex][colIndex]  = curPlayerCellValue;
			
			ArrayList<Coordinate> validPositions = this.getValidPositions(oppoPlayerCellValue, rowIndex, colIndex);
			for(Coordinate c : validPositions) {
				ArrayList<Coordinate> stack  = new ArrayList<Coordinate>();
				Coordinate pos = c;
				CellValue tile = board[pos.row][pos.col];
				while(!tile.equals(curPlayerCellValue)) {
					if(!tile.equals(oppoPlayerCellValue)) {
						stack.clear();
						break;
					}else {		
						stack.add(pos);
						pos = nextPos(pos);
						if(pos==null) {	
							stack.clear();
							break;
						}
						tile = board[pos.row][pos.col];
					}
				}
				for(Coordinate c1 : stack) {
					board[c1.row][c1.col] = curPlayerCellValue;
				}
				//update score
				int moveScore = stack.size();
				if(moveScore > 0) {
					if(this.currentPllayerID == playerID1) {
						this.playerScore1 = this.playerScore1 + moveScore + 1;
						this.playerScore2 = this.playerScore2 - moveScore;
					}else {
						this.playerScore2 = this.playerScore2 + moveScore + 1;
						this.playerScore1 = this.playerScore1 - moveScore;
					}
				}
			}
			
			ArrayList<Coordinate> positions = new ArrayList<Coordinate>();
			for(int i =0;i<8;i++)
				for(int j=0;j<8;j++) {
					if(board[i][j].equals(CellValue.POSSIBLE))
						board[i][j] = CellValue.EMPTY;
					else if(board[i][j].equals(oppoPlayerCellValue))
						positions.add(new Coordinate(i,j));
				}
		
			
			//reconstruct board by iterating for all tiles belonging to opponent player
			for(Coordinate c1 : positions) {
				ArrayList<Coordinate> validOppoPositions = this.getValidPositions(curPlayerCellValue, c1.row, c1.col);
				for(Coordinate c : validOppoPositions) {
					Coordinate pos = c;
					CellValue tile = board[pos.row][pos.col];
					while(! (tile.equals(CellValue.EMPTY) || tile.equals(CellValue.POSSIBLE)) ) {
						if(tile.equals(oppoPlayerCellValue)) {	
							pos = null;
							break;
						}
						else {
							pos = nextPos(pos);
							if(pos==null) {	
								break;
							}
							tile = board[pos.row][pos.col];
						}
					}
					if(pos!=null) {	
						board[pos.row][pos.col] = CellValue.POSSIBLE;
					}
				}
			}
			
			//swap players
			if(this.currentPllayerID == this.playerID1)
				this.currentPllayerID = this.playerID2;
			else
				this.currentPllayerID = this.playerID1;
			
		
			
	}
	
	/**
	 * helper method to return coordinates on all sides of [rowIndex,colIndex] that are of the opposite color of identifier 
	 * @param identifier
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private ArrayList<Coordinate> getValidPositions(CellValue oppoCellValue, int rowIndex, int colIndex){
		ArrayList<Coordinate> pos  = new ArrayList<Coordinate>();
		pos.add(new Coordinate(rowIndex-1,colIndex,Direction.N));
		pos.add(new Coordinate(rowIndex-1,colIndex+1,Direction.NE));
		pos.add(new Coordinate(rowIndex,colIndex+1,Direction.E));
		pos.add(new Coordinate(rowIndex+1,colIndex+1,Direction.SE));
		pos.add(new Coordinate(rowIndex+1,colIndex,Direction.S));
		pos.add(new Coordinate(rowIndex+1,colIndex-1,Direction.SW));
		pos.add(new Coordinate(rowIndex,colIndex-1,Direction.W));
		pos.add(new Coordinate(rowIndex-1,colIndex-1,Direction.NW));
		ArrayList<Coordinate> validPos = new ArrayList<Coordinate>();
		
		for(int i=0;i<8;i++) {
			try {
				if(this.board[pos.get(i).row][pos.get(i).col].equals(oppoCellValue)) {
					validPos.add(pos.get(i));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				//boundary conditions will result in arrayIndexOutOfBounds
			}	
		}
		
		
		return validPos;
	}
	
	/**
	 * helper method to return the cellValue of the tile next to pos
	 * @param pos
	 * @return
	 */
	private Coordinate nextPos(Coordinate pos) {
		Coordinate nextPos = null;
		switch(pos.dir) {
		case N:
			nextPos = new Coordinate(pos.row-1, pos.col, pos.dir);
			break;
		case NE:
			nextPos = new Coordinate(pos.row-1, pos.col+1, pos.dir);
			break;
		case E:
			nextPos = new Coordinate(pos.row, pos.col+1, pos.dir);
			break;
		case SE:
			nextPos = new Coordinate(pos.row+1, pos.col+1, pos.dir);
			break;
		case S:
			nextPos = new Coordinate(pos.row+1, pos.col, pos.dir);
			break;
		case SW:
			nextPos = new Coordinate(pos.row+1, pos.col-1, pos.dir);
			break;
		case W:
			nextPos = new Coordinate(pos.row, pos.col-1, pos.dir);
			break;
		case NW:
			nextPos = new Coordinate(pos.row-1, pos.col-1, pos.dir);
			break;
		default:
			break;
		}
		//boundary conditions
		if(nextPos.row < 0 || nextPos.row > 7 || nextPos.col < 0 || nextPos.col > 7)
			nextPos = null;
		return nextPos;
	}
	

}


class Coordinate{
	int row;
	int col;
	Direction dir;
	public Coordinate(int row, int col, Direction dir) {
		this.row = row;	this.col  = col;	this.dir  = dir;
	}
	public Coordinate(int row, int col) {
		this.row =  row;	this.col = col;
	}
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public Direction getDir() {
		return this.dir;
	}
	@Override
	public boolean equals(Object obj) {
		if(((Coordinate)obj).getCol() == this.col && ((Coordinate)obj).getRow() == this.row )
			return true;
		else
			return false;
	}
	
}

enum Direction{
	N,NE,E,SE,S,SW,W,NW;
}
