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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administracionRestaurante.model.Platillo;
import com.administracionRestaurante.service.PlatilloService;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })



@RestController //anotacion para indicar que esta clase es un controlador

@RequestMapping(path = "/restaurante/platillos/") //indica la ruta de nuestro endpoint par saber a donde se van a manejar esas solicitudes HTTP


public class PlatilloController {

	//Inyeccion de dependencias
	private final PlatilloService platilloService;

	@Autowired //anotacion para relacionar e inyectar dependencia 
	
	//Constructor que usa el objeto PlatilloService como parametro
	public PlatilloController (PlatilloService platilloService) {
		this.platilloService = platilloService;
	}//contructor
	

	
	//Solicitudes HTTP GET para traer todos los platillo
	@GetMapping
	public List<Platillo> getPlatillos(){
		return platilloService.leerPlatillos();
	}//get
	
	
	
	//Solicitudes HTTP GET para traer un platillo por Id
	@GetMapping(path = "{Id}")
	//Con @PathVariable le digo a mi metodo, que la ruta que va a variar es el id, con el tipo de dato Long id
	public Platillo getPlatillo(@PathVariable("Id")Long Id) {
		return platilloService.leerPlatillo(Id);
	}
	
	
	//Solicitud HTTP POST para crear un platillo
	@PostMapping
	public void postPlatillo(@RequestBody Platillo platillo) { //Que los datos que pasemos como cuerpo de nuestra solicitud, seran utilizados como campos de nuestro constructor
		platilloService.crearPlatillo(platillo);//disparo la operacion del CRUD que modifica mi BD	
	}
	
	
	
	//Solicitud HTTP PUT para modificar un platillo
	@PutMapping(path = "{Id}")
	public void updatePlatillo(@PathVariable("Id")Long Id, 
			@RequestParam (required = false) String nombre, 
			@RequestParam (required = false) String descripcion, 
			@RequestParam (required = false) Double precio, 
			@RequestParam (required = false) String url_imagen) {
		platilloService.actualizarPlatillo(Id, nombre, descripcion, url_imagen, precio);
	}
	
	
	
	//Solicitud HTTP DELETE para borrar un platillo
	@DeleteMapping(path = "{Id}") //borrar elementos por un id
	public void deletePlatillo(@PathVariable("Id")Long Id) {
	platilloService.borrarPlatillo(Id);
	}
}
