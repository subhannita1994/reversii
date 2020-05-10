package com.multiplayerGame.reversii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReversiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReversiiApplication.class, args);
	}
	
//	@RestController
//	@RequestMapping("/players")
//	public class PlayerController{
//		@Autowired
//		private PlayerRepository playerRepository;
//		@GetMapping
//		public Iterable<Player> getPlayers(){
//			return this.playerRepository.findAll();
//		}
//	}
//	
//	@RestController
//	@RequestMapping("/identifiers")
//	public class IdentifierController{
//		@Autowired
//		private IdentifierRepository identifierRepository;
//		@GetMapping
//		public Iterable<Identifier> getIdentifiers(){
//			return this.identifierRepository.findAll();
//		}
//	}
//	
//	@RestController
//	@RequestMapping("/playerTypes")
//	public class PlayerTypeController{
//		@Autowired
//		private PlayerTypeRepository playerTypeRepository;
//		@GetMapping
//		public Iterable<PlayerType> getPlayerTypes(){
//			return this.playerTypeRepository.findAll();
//		}
//	}
//	
//	@RestController
//	@RequestMapping("/gameConfigurations")
//	public class gameConfigurationController{
//		@Autowired
//		private GameConfigurationRepository gameConfigurationRepository;
//		@GetMapping
//		public Iterable<GameConfiguration> getGameConfiguration(){
//			return this.gameConfigurationRepository.findAll();
//		}
//	}

}
