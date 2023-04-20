package com.administracionRestaurante.model;

public class Token {

	//Atributo
	private final String accessToken;
	
	
	//Constructor
	public Token (String accessToken) {
		this.accessToken = accessToken;
	}


	//getter porque solo necesito consultar el token, no modificarlo
	public String getAccessToken() {
		return accessToken;
	}
	
	
	
}
