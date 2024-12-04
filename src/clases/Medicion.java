package clases;

import java.io.Serializable;
import java.time.LocalDate;

import utilidades.UtilidadesPanel;



public class Medicion {
	/**
	 * 
	 */
	
	//Atributos
	private LocalDate fecha;
	private float peso;
	
	//Constructores
	
	
	//Métodos
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
		this.fecha=UtilidadesPanel.pidoFechaDMA("Introduce la fecha del pesaje");
		this.peso=UtilidadesPanel.leerFloat("Introduce el peso en Kilogramos: ");
	}

}
