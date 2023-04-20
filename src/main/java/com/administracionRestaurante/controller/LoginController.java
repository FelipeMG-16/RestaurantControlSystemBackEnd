package com.administracionRestaurante.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracionRestaurante.jwt.config.JwtFilter;
import com.administracionRestaurante.model.Token;
import com.administracionRestaurante.model.User;
import com.administracionRestaurante.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;




@RestController
@RequestMapping(path="/restaurante/login/")



public class LoginController {

	//Constante para el autowired
	
	private final UserService userService;
	
	//Agregamos la anotacion para la inyeccion
	@Autowired
	
	//Creamos un constructor 
	public LoginController (UserService userService) {
		this.userService = userService;
	}
	
	
//	//Metodo post para iniciar sesion (antes del JwtFilter)
//		@PostMapping
//		public String Login(@RequestBody User user) {
//			String respuesta = "Nombre de usuario o contrasenia incorrectos";
//			if (userService.loginCifrado(user.getUsername(), user.getPassword())){
//				respuesta = "OK, nombre de usuario y contrasenia correctos";
//			}
//			return respuesta;
//		}
		
	
	//Metodo post para iniciar sesion (despues del JwtFilter)
	@PostMapping
	public Token Login(@RequestBody User user) throws ServletException {
		if (userService.loginCifrado(user.getUsername(), user.getPassword())) {
			return new Token(generateToken(user.getUsername()));
		}
		throw new ServletException("Nombre de usuario o contrasenia incorrectos");
	}
	
	
	
		
		//Metodo para generar un token
		private String generateToken(String username) { //token asociado al usuario
			Calendar calendar = Calendar.getInstance(); //instanciar un objeto del tipo calendario
			calendar.add(Calendar.HOUR, 10); //para darle vigencia a mi token
			
			return Jwts.builder().setSubject(username).claim("role", "user")
					.setIssuedAt(new Date()).setExpiration(calendar.getTime())
					.signWith(SignatureAlgorithm.HS256, JwtFilter.secret).compact();
		}	
		
} 
