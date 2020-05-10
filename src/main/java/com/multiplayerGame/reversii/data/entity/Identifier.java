package com.multiplayerGame.reversii.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IDENTIFIER")
public class Identifier {
	
	@Id
	@Column(name="identifier")
	private String identifier;	//should be color

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
