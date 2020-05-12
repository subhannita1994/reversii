package com.multiplayerGame.reversii.business.service;


import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.multiplayerGame.reversii.business.domain.Reversii;
import com.multiplayerGame.reversii.data.entity.Game;
import com.multiplayerGame.reversii.data.entity.GameConfiguration;
import com.multiplayerGame.reversii.data.entity.Player;
import com.multiplayerGame.reversii.data.repository.GameConfigurationRepository;
import com.multiplayerGame.reversii.data.repository.GameRepository;
import com.multiplayerGame.reversii.data.repository.IdentifierRepository;
import com.multiplayerGame.reversii.data.repository.PlayerRepository;
import com.multiplayerGame.reversii.data.repository.PlayerTypeRepository;

@Service
public class GameService {
	
	private PlayerRepository playerRepository;
	private GameRepository gameRepository;
	private GameConfigurationRepository gameConfigurationRepository;
	private IdentifierRepository identifierRepository;
	private PlayerTypeRepository playerTypeRepository;
	private Random rand;
	private HashMap<Integer, Reversii> games = new HashMap<Integer, Reversii>();
	
	
	public GameService(PlayerRepository playerRepository, GameRepository gameRepository, GameConfigurationRepository gameConfigurationRepository, PlayerTypeRepository playerTypeRepository, IdentifierRepository identifierRepository) {
		this.playerRepository = playerRepository;
		this.gameRepository = gameRepository;
		this.gameConfigurationRepository = gameConfigurationRepository;
		this.identifierRepository = identifierRepository;
		this.playerTypeRepository = playerTypeRepository;
		rand = new Random();
	}
	
	
	public Reversii startGameSinglePlayer(String gameName, String playerName) {
		
		GameConfiguration gameConfiguration = this.gameConfigurationRepository.findById(gameName).get();
		Game game = new Game();
		game.setGameConfiguration(gameConfiguration.getGameName());
		this.gameRepository.save(game);
		
		Reversii startGame = new Reversii(gameConfiguration, gameName);
		startGame.setGameID(game.getGameID());
		
		List<String> identifiers = this.identifierRepository.findTopFew(gameConfiguration.getNumberOfPlayers());
		int score = gameConfiguration.getStartingScore();
		
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
		System.out.println("game service: singlePlayer--"+player1.getPlayerID()+player1.getPlayerName()+player1.getIdentifier());
		System.out.println("game service: singlePlayer--"+player2.getPlayerID()+player2.getPlayerName()+player2.getIdentifier());
		game.setCurrentPlayerID(player1);
		this.gameRepository.save(game);
		startGame.setPlayerID1(player1.getPlayerID());
		startGame.setPlayerID2(player2.getPlayerID());
		startGame.setCurrentPlayer(player1.getPlayerID());
		startGame.setPlayerIdentifier1(player1.getIdentifier());
		startGame.setPlayerIdentifier2(player2.getIdentifier());
		startGame.setPlayerName1(player1.getPlayerName());
		startGame.setPlayerName2(player2.getPlayerName());
		
		games.put(game.getGameID(), startGame);
		
		return startGame;
	}
	
	public Reversii startGameMultiPlayer(String gameName, String playerName) {
		GameConfiguration gameConfiguration = this.gameConfigurationRepository.findById(gameName).get();
		Game game = new Game();
		game.setGameConfiguration(gameConfiguration.getGameName());
		this.gameRepository.save(game);
		
		Reversii startGame = new Reversii(gameConfiguration, gameName);
		startGame.setGameID(game.getGameID());
		
		List<String> identifiers = this.identifierRepository.findTopFew(gameConfiguration.getNumberOfPlayers());
		int score = gameConfiguration.getStartingScore();
		
		Player playerA = new Player();
		playerA.setGame_id(game.getGameID());
		playerA.setPlayerName(playerName);
		playerA.setPlayerType(this.playerTypeRepository.findById("HUMAN").get().getPlayerType());
		playerA.setScore(score);
		playerA.setIdentifier(identifiers.get(0));
		this.playerRepository.save(playerA);
		System.out.println("game service: multiPlayer--"+playerA.getPlayerID()+playerA.getPlayerName()+playerA.getIdentifier());
		
		game.setCurrentPlayerID(playerA);
		this.gameRepository.save(game);
		startGame.setPlayerID1(playerA.getPlayerID());
		startGame.setCurrentPlayer(playerA.getPlayerID());
		startGame.setPlayerIdentifier1(playerA.getIdentifier());
		startGame.setPlayerName1(playerA.getPlayerName());
		
		games.put(game.getGameID(), startGame);
		
		return startGame;
	}
	
	public Reversii startGameMultiPlayerExisting(String gameName, String playerName, int gameID) {
		GameConfiguration gameConfiguration = this.gameConfigurationRepository.findById(gameName).get();
		Game game = this.gameRepository.findById(gameID).get();
		Reversii startGame = this.games.get(gameID);
		int score = gameConfiguration.getStartingScore();
		List<String> identifiers = this.identifierRepository.findTopFew(gameConfiguration.getNumberOfPlayers());
		
		Player playerB = new Player();
		playerB.setGame_id(game.getGameID());
		playerB.setPlayerName(playerName);
		playerB.setPlayerType(this.playerTypeRepository.findById("HUMAN").get().getPlayerType());
		playerB.setScore(score);
		playerB.setIdentifier(identifiers.get(1));
		this.playerRepository.save(playerB);
		System.out.println("game service: existing multiPlayer--"+playerB.getPlayerID()+playerB.getPlayerName()+playerB.getIdentifier());
		
		startGame.setPlayerID2(playerB.getPlayerID());
		startGame.setPlayerIdentifier2(playerB.getIdentifier());
		startGame.setPlayerName2(playerB.getPlayerName());
		
		return startGame;
		
	}
	
	
	public Reversii move(int gameID, int row, int col) {
		Reversii gameLogic = games.get(gameID);
		gameLogic.move(row, col);
		
		Game game = this.gameRepository.findById(gameID).get();
		game.setCurrentPlayerID(this.playerRepository.findById(gameLogic.getCurrentPlayer()).get());
		Player player1 = this.playerRepository.findById(gameLogic.getPlayerID1()).get();
		Player player2 = this.playerRepository.findById(gameLogic.getPlayerID2()).get();
		player1.setScore(gameLogic.getPlayerScore1());
		player2.setScore(gameLogic.getPlayerScore2());
		this.gameRepository.save(game);
		
		return gameLogic;
	}

}
