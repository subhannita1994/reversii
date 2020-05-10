package com.multiplayerGame.reversii.business.domain;

import java.util.ArrayList;

public class Board {
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	
	
	
	public Cell getCell(int index) {
		return cells.get(index);
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	public void addCell(Cell c) {
		this.cells.add(c);
	}

}
