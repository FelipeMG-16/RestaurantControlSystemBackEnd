package com.administracionRestaurante.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administracionRestaurante.model.User;
import com.administracionRestaurante.service.UserService;


@RestController
@RequestMapping(path="/restaurante/usuarios/")
@CrossOrigin("*")

public class UserController {
	
	
	public final UserService userService;//constante para el autowired
	
	@Autowired
	
	//constructor para el autowired
	public UserController(UserService userService) {
		this.userService = userService;
	} //constructor
	
	
	
	@GetMapping
	public List<User> getUsuarios() {
		return userService.leerUsuarios();
	}// getUsuarios
	
	
	@GetMapping(path="{userId}")
	public User getUsuario(@PathVariable ("userId") Long userId) {
		return userService.leerUsuario(userId);
	}// getUsuario
	
	
	@DeleteMapping(path="{userId}")
	public void deleteUsuario(@PathVariable ("userId") Long userId) {
		userService.borrarUsuario(userId);
	}//borrarUsuario
	
	
	@PostMapping
	public void postUsuario(@RequestBody User user) {
		userService.agregarUsuario(user);
	}// agregarUsuario
	
	
	@PutMapping(path="{userId}")
	public void putUsuario (@PathVariable ("userId") Long userId,
			@RequestParam String currentPassword,
			@RequestParam String newPassword) {
			userService.actualizarUsuario(userId, currentPassword, newPassword);
	}//actualizarUsuario
	
	
}//class UserController