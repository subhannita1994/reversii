package com.multiplayerGame.reversii.business.controllers;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.multiplayerGame.reversii.business.domain.Board;
import com.multiplayerGame.reversii.business.domain.Cell;
import com.multiplayerGame.reversii.business.domain.CellValue;
import com.multiplayerGame.reversii.business.domain.Reversii;
import com.multiplayerGame.reversii.business.service.GameService;

@Controller
public class GameController {
	
	private final GameService startGameService;
	private HashMap<Integer, Reversii> games = new HashMap<Integer, Reversii>();
	@Autowired
	public GameController(GameService startGameService) {
		this.startGameService =  startGameService;
	}
	
	
	

	@RequestMapping(value = "/Reversii/start" , method = RequestMethod.GET )
	public String getGameSinglePlayer(@RequestParam(value="gameName", required=true)String gameName, @RequestParam(value="playerName", required=true) String playerName) {
		
		Reversii startGame = this.startGameService.startGameSinglePlayer(gameName, playerName);
		this.games.put(startGame.getGameID(), startGame);
		
		return "redirect:/Reversii?gameID="+startGame.getGameID();
	}
	
	@RequestMapping(value = "/Reversii/move" , method = RequestMethod.GET )
	public String handleClick(@RequestParam(value="gameID", required=true)int gameID,@RequestParam(value="row", required=true)int row,@RequestParam(value="col", required=true)int col ) {
		System.out.println("game controller: clicked on "+row+col);
		Reversii game = this.startGameService.move(gameID, row, col);
		this.games.put(game.getGameID(), game);
		
		return "redirect:/Reversii?gameID="+game.getGameID();
	}
	
	@RequestMapping(value="/Reversii", method=RequestMethod.GET)
	public String render(@RequestParam(value="gameID", required=true)int gameID, Model model) {
		System.out.println("rendering...current player:"+games.get(gameID).getCurrentPlayer());
		model.addAttribute("game", games.get(gameID));
		
		CellValue[][] boardValues = games.get(gameID).getBoard();
		ArrayList<Board> rows = new ArrayList<Board>();
		for(int i=0;i<boardValues.length; i++) {
			Board b = new Board();
			for(int j=0;j<boardValues[0].length; j++) {
				b.addCell(new Cell(i,j,boardValues[i][j].toString()+".png"));
			}
			rows.add(b);
		}
		model.addAttribute("board", rows);
		return "startReversii";
	}
	
	
	
}
