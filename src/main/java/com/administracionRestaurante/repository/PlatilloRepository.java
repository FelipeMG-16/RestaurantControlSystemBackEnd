package com.administracionRestaurante.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.administracionRestaurante.model.Platillo;

//Indicamos que extendemos de la interface del JpaRepository, porque vamos a manjear la entidad llamada Platillo y la vamos a relacionar con una base de datos en MySQL llamada de la misma forma.


//Cuando heredamos del JpaRepository, usamos los metodos definidos anteriormente, pasando como atributo o parametro el tipo de dato sobre el que el que hacemos la consulta, y su referencia en la base de datos (primary key)

public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
	
	
	//Query personalizada
	//La anotacion @Query indinca que vamos a utilizar una consulta personalizada en lugar de un metodo ya especifico del JPA repository usnado JPQL
	
	// p es un alias para especificar que despues de la palabra FROM en esta consulta, hacemos referencia a un objeto dentro de esa tabla

	@Query("SELECT platillo FROM Platillo platillo WHERE platillo.nombre=?1")
	
	//Metodo que realiza la consulta personalizada definida anteriormente. Retornar un Optional que puede contener un objeto PLatillo si se encuentra en la base de datos (tiene que ser con el nombre especificado), o un Optional vacio si no existe
	
	Optional<Platillo> findByName(String nombre);
}
