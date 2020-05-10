package com.multiplayerGame.reversii.web;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multiplayerGame.reversii.business.domain.Board;
import com.multiplayerGame.reversii.business.domain.CellValue;
import com.multiplayerGame.reversii.business.domain.StartReversii;
import com.multiplayerGame.reversii.business.service.StartGameService;

@Controller
@RequestMapping("/startReversii")
public class StartGameWebController {
	
	private final StartGameService startGameService;
	@Autowired
	public StartGameWebController(StartGameService startGameService) {
		this.startGameService =  startGameService;
	}
	
	
	
	@GetMapping
	public String getGameSinglePlayer(@RequestParam(value="gameName", required=true)String gameName, @RequestParam(value="playerName", required=true) String playerName, Model model) {
		
		StartReversii startGame = this.startGameService.startGameSinglePlayer(gameName, playerName);
		model.addAttribute("StartGame", startGame);
		
		CellValue[][] boardValues = startGame.getBoard();
		ArrayList<Board> board = new ArrayList<Board>();
		for(int i=0;i<boardValues.length; i++) {
			for(int j=0;j<boardValues[0].length; j++) {
				board.add(new Board(i,j,boardValues[i][j].toString()+".png"));
			}
		}
		model.addAttribute("board", board);
		
		
		return "startReversii";
	}
	
	
}
