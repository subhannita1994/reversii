package com.multiplayerGame.reversii.business.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.ErrorManager;

import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
import com.multiplayerGame.reversii.business.message.GameResponse;
import com.multiplayerGame.reversii.business.message.PlayerMessage;
import com.multiplayerGame.reversii.business.service.GameService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class GameController {


	private final GameService startGameService;
	private HashMap<Integer, Reversii> games = new HashMap<Integer, Reversii>();
	
	
	@Autowired
	public GameController(GameService startGameService) {
		this.startGameService =  startGameService;
	}
	
	
	

	@RequestMapping(value = "/Reversii/startSinglePlayer" , method = RequestMethod.GET )
	public String getGameSinglePlayer(@RequestParam(value="gameName", required=true)String gameName, @RequestParam(value="playerName", required=true) String playerName, Model model) {
		
		Reversii startGame = this.startGameService.startGameSinglePlayer(gameName, playerName);
		this.games.put(startGame.getGameID(), startGame);
		
		model.addAttribute("game", startGame);
		model.addAttribute("board", this.makeBoard(startGame));
		model.addAttribute("playerID", startGame.getPlayerIDByName(playerName));
		
		return "startReversii";
	}
	
	@RequestMapping(value="/Reversii/startMultiPlayer", method=RequestMethod.GET)
	public String getGameMultiPlayer(@RequestParam(value="gameName", required=true)String gameName, @RequestParam(value="playerName", required=true)String playerName, Model model) {
		Reversii startGame = this.startGameService.startGameMultiPlayer(gameName, playerName);
		this.games.put(startGame.getGameID(), startGame);

		model.addAttribute("game", startGame);
		model.addAttribute("board", this.makeBoard(startGame));
		model.addAttribute("playerID", startGame.getPlayerIDByName(playerName));
		
		return "startReversii";
	}
	
	@RequestMapping(value="/Reversii/startMultiPlayerExistingGame", method=RequestMethod.GET)
	public String getGameMultiPlayerExisting(@RequestParam(value="gameName", required=true)String gameName, @RequestParam(value="playerName", required=true)String playerName, @RequestParam(value="gameID", required=true)int gameID, Model model) {
		Reversii startGame = this.startGameService.startGameMultiPlayerExisting(gameName, playerName, gameID);
		this.games.put(gameID, startGame);
		model.addAttribute("game", startGame);
		model.addAttribute("board", this.makeBoard(startGame));
		model.addAttribute("playerID", startGame.getPlayerIDByName(playerName));
		
		return "startReversii";
	}
	
	
	@MessageMapping("/move/{gameID}")
	@SendTo("/Reversii/{gameID}")
	public GameResponse move(@DestinationVariable String gameID, PlayerMessage message) {
		System.out.println("game controller: "+message.getGameID()+":"+message.getPlayerID()+"clicked on "+message.getRow()+message.getCol());
		Reversii game = this.startGameService.move(message.getGameID(), message.getPlayerID(), message.getRow(), message.getCol());
		if(game!=null) {
			this.games.put(game.getGameID(), game);
			CellValue[][] boardValues = game.getBoard();
			String[][] board = new String[boardValues.length][boardValues[0].length];
			for(int i=0;i<boardValues.length; i++) {
				for(int j=0;j<boardValues[0].length; j++) {
					board[i][j] = boardValues[i][j].toString()+".png";
				}
			}
			return new GameResponse(true, game.getGameID(), board);
		}else
			return new GameResponse(false, message.getGameID(), null);
		
	}
	
	
	private ArrayList<Board> makeBoard(Reversii startGame) {
		CellValue[][] boardValues = startGame.getBoard();
		ArrayList<Board> rows = new ArrayList<Board>();
		for(int i=0;i<boardValues.length; i++) {
			Board b = new Board();
			for(int j=0;j<boardValues[0].length; j++) {
				b.addCell(new Cell(i,j,boardValues[i][j].toString()+".png"));
			}
			rows.add(b);
		}
		
		return rows;
	}

	
	
	
}
