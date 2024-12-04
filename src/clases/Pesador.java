package clases;

import java.time.LocalDate;

import utilidades.UtilidadesPanel;



public class Pesador extends Persona{
	/**
	 * 
	 */
	
	//Atributos
	private LocalDate fecAlta;

	//Constructor
	
	
	//Métodos
	public LocalDate getFecAlta() {
		return fecAlta;
	}

	public void setFecAlta(LocalDate fecAlta) {
		this.fecAlta = fecAlta;
	}
	
	public void setDatos(){
		super.setDatos();
		this.fecAlta=UtilidadesPanel.pidoFechaDMA("Introduce la fecha de alta en la empresa");
	}

	@Override
	public String toString() {
		super.toString();
		return "Pesador [fecAlta=" + fecAlta + "]";
	}
	
	

}
