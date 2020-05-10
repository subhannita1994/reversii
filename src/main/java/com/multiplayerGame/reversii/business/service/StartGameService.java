package com.multiplayerGame.reversii.business.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.multiplayerGame.reversii.business.domain.StartReversii;
import com.multiplayerGame.reversii.data.entity.Game;
import com.multiplayerGame.reversii.data.entity.GameConfiguration;
import com.multiplayerGame.reversii.data.entity.Player;
import com.multiplayerGame.reversii.data.repository.GameConfigurationRepository;
import com.multiplayerGame.reversii.data.repository.GameRepository;
import com.multiplayerGame.reversii.data.repository.IdentifierRepository;
import com.multiplayerGame.reversii.data.repository.PlayerRepository;
import com.multiplayerGame.reversii.data.repository.PlayerTypeRepository;

@Service
public class StartGameService {
	
	private PlayerRepository playerRepository;
	private GameRepository gameRepository;
	private GameConfigurationRepository gameConfigurationRepository;
	private IdentifierRepository identifierRepository;
	private PlayerTypeRepository playerTypeRepository;
	private Random rand;
	
	public StartGameService(PlayerRepository playerRepository, GameRepository gameRepository, GameConfigurationRepository gameConfigurationRepository, PlayerTypeRepository playerTypeRepository, IdentifierRepository identifierRepository) {
		this.playerRepository = playerRepository;
		this.gameRepository = gameRepository;
		this.gameConfigurationRepository = gameConfigurationRepository;
		this.identifierRepository = identifierRepository;
		this.playerTypeRepository = playerTypeRepository;
		rand = new Random();
	}
	
	
	public StartReversii startGameSinglePlayer(String gameName, String playerName) {
		
		Optional<GameConfiguration> gameConfiguration = this.gameConfigurationRepository.findById(gameName);
		Game game = new Game();
		game.setGameConfiguration(gameConfiguration.get().getGameName());
		this.gameRepository.save(game);
		
		StartReversii startGame = new StartReversii();
		startGame.setGameID(game.getGameID());
		
		List<String> identifiers = this.identifierRepository.findTopFew(gameConfiguration.get().getNumberOfPlayers());
		int score = gameConfiguration.get().getStartingScore();
		
		Player playerHuman = new Player();
		playerHuman.setGame_id(game.getGameID());
		playerHuman.setPlayerName(playerName);
		playerHuman.setPlayerType(this.playerTypeRepository.findById("HUMAN").get().getPlayerType());
		playerHuman.setScore(score);
		
		Player playerAI = new Player();
		playerAI.setGame_id(game.getGameID());
		playerAI.setPlayerName("Bill");
		playerAI.setPlayerType(this.playerTypeRepository.findById("AI").get().getPlayerType());
		playerAI.setScore(score);
		
		
		Player player1, player2;
		
		if(rand.nextInt(1) == 0) {
			player1 = playerHuman;	player2 = playerAI;
		}else {
			player1 = playerAI;	player2 = playerHuman;
		}
		
		player1.setIdentifier(identifiers.get(0));
		player2.setIdentifier(identifiers.get(1));
		this.playerRepository.save(player1);
		this.playerRepository.save(player2);
		startGame.setPlayerID1(player1.getPlayerID());
		startGame.setPlayerID2(player2.getPlayerID());

		
		return startGame;
	}

}
