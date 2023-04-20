package com.administracionRestaurante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administracionRestaurante.model.User;
import com.administracionRestaurante.repository.UserRepository;
import com.administracionRestaurante.utils.SHAUtil;



@Service
public class UserService {
	
	
	//Constante para el Autowired
	private final UserRepository userRepository;

	@Autowired
	
	//Inyeccion de dependencias
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}//constructor
	
	
	//Metodo para iniciar sesion
	public boolean login(String username, String password) { //recibimos el username y el password
		boolean respuesta=false; //creamos una variable booleana y la iniciamos en false
		Optional<User> user = userRepository.findByUsername(username); //buscamos en el repository si el usuario existe por nombre
		//si el usuario existe
		if (user.isPresent()) {
			//System.out.println("Password SHA: " + SHAUtil.createHash(password));
			//validamos que la constrasena proporcionada sea igual que la contrasena en la base de datos
			if (user.get().getPassword().equals(password)){
				//si la respuesta es correcta, cambiamos respuesta a true
				respuesta=true;
			}//if password
		}//if isPresent
		return respuesta; //imprimimos si el inicio de sesion fue exitoso (true) o no (false)
	}//login
	
	

	//Metodo para obtener todos los usuarios
	public List<User> leerUsuarios() {
		return userRepository.findAll();
	}//getUsers

	
	//Metodo para obtener un usuario por id
	public User leerUsuario(Long userId) {
		return userRepository.findById(userId).orElseThrow(
			()-> new IllegalStateException("El usuario con el id " + userId + " no existe")
			);
	}// getUser

	
	//Metodo para borrar un usuario por id
	public void borrarUsuario(Long userId) {
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		} else {
			throw new IllegalStateException("El usuario con el id " + userId + " no existe");
		} //else 
	} // deleteUser

	
//	//Metodo para crear un usuario
//	public void agregarUsuario(User user) {
//		Optional<User> userByName = userRepository.findByUsername(user.getUsername());
//		if (userByName.isPresent()) {
//			throw new IllegalStateException("El usuario con el nombre " + user.getUsername() 
//		+ " ya existe, puedes intentar con otro usuario"); 
//		}// if
//		user.setPassword((user.getPassword()) );	
//		userRepository.save(user);	
//	}// addUser

	
	//Metodo para crear un usuario cifrado
	public void agregarUsuario(User user) {
		Optional<User> userByName = userRepository.findByUsername(user.getUsername());
		if (userByName.isPresent()) {
			throw new IllegalStateException("El usuario con el nombre " + user.getUsername() 
		+ " ya existe, puedes intentar con otro usuario"); 
		}// if
		user.setPassword( SHAUtil.createHash(user.getPassword()) );	
		userRepository.save(user);	
	}// addUser

	
	
	
	//Metodo para iniciar sesion CIFRADA
	public boolean loginCifrado(String username, String password) {
		boolean respuesta=false;
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			System.out.println("Password SHA: " + SHAUtil.createHash(password));
			if (SHAUtil.verifyHash(password, user.get().getPassword()) ) {
			//if (user.get().getPassword().equals(password)) {
				respuesta=true;
			}// if password
		}//if isPresent
		return respuesta;
	}//login
	
	
	
	//Metodo para actualizar un usuario ya existente
	public void actualizarUsuario(Long userId, String currentPassword, String newPassword) {
	    if (userRepository.existsById(userId)) {
	        User user = userRepository.getById(userId);
	        if ((newPassword != null) && (currentPassword != null)) {
	            if (currentPassword.equals(user.getPassword())) {
	                user.setPassword(newPassword);
	                userRepository.save(user);
	            } else {
	                throw new IllegalStateException("Contraseña incorrecta");
	            }
	        } else {
	            throw new IllegalStateException("Contraseñas nulas");
	        }
	    } else {
	        throw new IllegalStateException("Usuario no encontrado " + userId);
	    }
	}

	
	
	

	
	
	
	
}