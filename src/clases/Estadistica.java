package clases;

public class Estadistica {
	//Atributos
	private float indiceInf;
	private float indiceSup;
	private String clasificacion;
	private int cuantos;
	
	//Constructores
	
	public Estadistica(float indiceInf, float indiceSup, String clasificacion, int cuantos) {
		this.indiceInf = indiceInf;
		this.indiceSup = indiceSup;
		this.clasificacion = clasificacion;
		this.cuantos = cuantos;
	}
	
	public Estadistica() {
		this.indiceInf = 0;
		this.indiceSup = 0;
		this.clasificacion = "";
		this.cuantos = 0;
	}
	
	//Métodos
	public float getIndiceInf() {
		return indiceInf;
	}

	public void setIndiceInf(float indiceInf) {
		this.indiceInf = indiceInf;
	}

	public float getIndiceSup() {
		return indiceSup;
	}

	public void setIndiceSup(float indiceSup) {
		this.indiceSup = indiceSup;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public int getCuantos() {
		return cuantos;
	}

	public void setCuantos(int cuantos) {
		this.cuantos = cuantos;
	}

	@Override
	public String toString() {
		return "Estadistica [indiceInf=" + indiceInf + ", indiceSup=" + indiceSup + ", clasificacion=" + clasificacion
				+ ", cuantos=" + cuantos + "]";
	}

}
