package com.multiplayerGame.reversii.business.domain;

public class Board {
	private int row;
	private int col;
	private String color;
	
	public Board(int row, int col, String color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
