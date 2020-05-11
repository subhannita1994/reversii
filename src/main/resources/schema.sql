CREATE TABLE GAME_CONFIGURATION(
  game_name VARCHAR(20) PRIMARY KEY NOT NULL,
  board_rows INTEGER,
  board_columns INTEGER,
  tile_size INTEGER DEFAULT 50,
  number_of_players INTEGER NOT NULL,
  starting_score INTEGER DEFAULT 0);
  
  CREATE TABLE IDENTIFIER(
  identifier VARCHAR(20) PRIMARY KEY 
  );
  
  CREATE TABLE PLAYER_TYPE(
  player_type VARCHAR(20) PRIMARY KEY);
  
  
  CREATE TABLE PLAYER(
  player_id INTEGER AUTO_INCREMENT PRIMARY KEY,
  player_name VARCHAR(50) NOT NULL,
  identifier VARCHAR(20) NOT NULL,
  player_type VARCHAR(20) NOT NULL,
  score INTEGER NOT NULL,
  game_id INTEGER NOT NULL);
  
  CREATE TABLE GAME(
  game_id INTEGER AUTO_INCREMENT PRIMARY KEY,
  game_configuration VARCHAR(20) NOT NULL,
  current_player_id INTEGER);
  
  ALTER TABLE PLAYER ADD FOREIGN KEY(identifier) REFERENCES IDENTIFIER(identifier);
  ALTER TABLE PLAYER ADD FOREIGN KEY(player_type) REFERENCES PLAYER_TYPE(player_type);
  ALTER TABLE PLAYER ADD FOREIGN KEY(game_id) REFERENCES GAME(game_id);
  ALTER TABLE GAME ADD FOREIGN KEY(current_player_id) REFERENCES PLAYER(player_id);
  
  CREATE SEQUENCE PLAYER_SEQUENCE_ID START WITH (select max(player_id) + 1 from PLAYER);
  CREATE SEQUENCE GAME_SEQUENCE_ID START WITH (select max(game_id) + 1 from GAME);
  
  