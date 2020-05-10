package com.multiplayerGame.reversii.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "startReversii";
	}
	
	
}
