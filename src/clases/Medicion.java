package clases;

import java.io.Serializable;
import java.time.LocalDate;

import utilidades.Utilidades;



public class Medicion {
	/**
	 * 
	 */
	
	//Atributos
	private LocalDate fecha;
	private float peso;
	
	//Constructores
	
	
	//M�todos
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public void setDatos() {
		this.fecha=Utilidades.pidoFechaDMA("Fecha nueva medición en formato: dd-mm-aaaa:");
		this.peso=Utilidades.leerFloat("Introduce el peso en Kilogramos: ");
	}

}
