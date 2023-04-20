package com.administracionRestaurante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administracionRestaurante.model.Platillo;
import com.administracionRestaurante.repository.PlatilloRepository;

//Le indicamos al Application Context que esta clase es un Servicio
@Service


public class PlatilloService {

	
	//Que voy a relacionar, como lo voy a relacionar, cuando lo voy a utilizar
	
	//Primero declaro una varible del tipo PlatilloRepository para usarla cuando la necesite
	private final PlatilloRepository platilloRepository;
	

	//Creo un cable para conectar entidades (PlatilloRepository - PlatilloService)
	@Autowired
	
	
	//Uso ese objeto como parametro de mi constructor
	public PlatilloService (PlatilloRepository platilloRepository) {
		this.platilloRepository = platilloRepository;
	}//constructor con la dependencia inyectada como parametro
	

	//Logica de nuestro negocio - Operaciones del CRUD
	
	
	//Crear platillo (Create)
	public void crearPlatillo(Platillo platillo) {
		//Necesitamos saber si el producto buscado existe, SI EXISTE lo ponemos dentro del optional
		Optional<Platillo> platilloBuscado =
				platilloRepository.findByName(platillo.getNombre()); //buscamos por nombre
		
		if (platilloBuscado.isPresent()) { //si el optional tiene un objeto almacenado (vacio = false)
		throw new IllegalStateException("El platillo con el nombre " + platillo.getNombre() + " ya existe.");
		} else { //si el optional esta vacio (vacio = true)
			platilloRepository.save(platillo); //guardo esa informacin en mi base de datos usando mi repository como la interface que es
		}
		
	}
	
	
	
	//Leer platillos (Read)
	public List<Platillo> leerPlatillos(){ //
		return platilloRepository.findAll();
	}//leerPlatillos
	
	
	//Leer platillo (Read)
	public Platillo leerPlatillo(Long Id) {
		return platilloRepository.findById(Id).orElseThrow(()-> new IllegalStateException("El Producto " + "con el id " + Id + " no existe.") );
	}//leerPlatillo
	
	
	
	//Actualizar platillo (Update)
	
	//Necesitamos pasar todos los parametros, para contemplar todas las modificaciones posibles
	public void actualizarPlatillo(Long Id, String nombre, String descripcion, String url_imagen, Double precio) {		
		//Si el producto existe, entonces se modifica
		if (platilloRepository.existsById(Id)) {
			//entonces lo modifico
			
			//traigo el objeto, y lo pongo en la variable temporal
			Platillo platilloABuscar = platilloRepository.getById(Id);
			if (nombre!=null) platilloABuscar.setNombre(nombre);
			if (descripcion!=null) platilloABuscar.setDescripcion(descripcion);
			if (precio!=null) platilloABuscar.setPrecio(precio);
			if (url_imagen!=null) platilloABuscar.setUrl_imagen(url_imagen);
			
			//cuando termino de editar el objeto...
			platilloRepository.save(platilloABuscar);
			//Si el producto NO existe, no puedo modificar nada y muestro un mensaje
		}else {
			System.out.println("El producto con el id " + Id + " no existe");
		}	
	}
	

	
	//Borrar platillo (Delete)
	public void borrarPlatillo(Long Id) {
		//Buscamos un platillo por id, y si existe...
		if (platilloRepository.existsById(Id)) {//true
			//Lo borramos
			platilloRepository.deleteById(Id);
		}
	}

	
	
	
	
}
