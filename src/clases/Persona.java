package clases;


import java.time.LocalDate;
import java.time.Period;

import utilidades.UtilidadesPanel;

public class Persona{
	
	//Atributos
	private String nombre;
	private LocalDate fecNacimiento;
	
	//Constructores
	
	
	//M�todos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(LocalDate fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	
	public void setDatos() {
		this.nombre=UtilidadesPanel.introducirCadena("Introduce el nombre: ");
		this.fecNacimiento=UtilidadesPanel.pidoFechaDMA("Introduce la fecha de nacimiento ");
	}
	
	public int calcularEdad() {
		Period periodo= Period.between(fecNacimiento, LocalDate.now());
		return periodo.getYears();
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", fecNacimiento=" + fecNacimiento + "]";
	}
	
}
