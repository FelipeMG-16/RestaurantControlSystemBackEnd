package com.administracionRestaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //esta clase Platillo se convierte en una entidad de JPA para manejar una persistencia de datos (que esta informacion se quede guardada en una BD)

@Table(name="Platillo")//espeficificamos el nombre de la tabla de MySQL a donde llega esta informacion


public class Platillo {
	@Id //especificamos que nuestra llave primaria es el campo id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//le indicamos a JAVA que vamos a utilizar una estratega de generacion de valores de identidad de la base de datos (para generar valores unicos e incrementables para nuestras llaves primarias)
	@Column(name = "id", unique = true, nullable = false) //especificamos que el campo donde se aplica la estrategia de generacion de valores autoincrementables es una columna llamada id, unica y no nula
	

	
	//Atributos
	private Long id;
	private String nombre;
	private String descripcion;
	private String url_imagen;
	private Double precio;
	

	//Constructor vacio
	public Platillo() {
		
	}//constructor vacio
	
	
	
	//Constructores
	public Platillo(Long id, String nombre, String descripcion, String url_imagen, Double precio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url_imagen = url_imagen;
		this.precio = precio;
	}//constructor

	
	//Getters y Setters
	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl_imagen() {
		return url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	//toString
	@Override
	public String toString() {
		return "Platillo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", url_imagen="
				+ url_imagen + ", precio=" + precio + "]";
	}
}
