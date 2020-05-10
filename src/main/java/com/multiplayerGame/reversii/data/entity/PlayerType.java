package com.multiplayerGame.reversii.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PLAYER_TYPE")
public class PlayerType {

	@Id
	@Column(name="player_type")
	private String playerType;

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
}
