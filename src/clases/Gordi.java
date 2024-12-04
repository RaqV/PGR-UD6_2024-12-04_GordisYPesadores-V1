package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utilidades.UtilidadesPanel;



public class Gordi extends Persona{
	
	
	//Atributos
	private String codigo;
	private float altura;
	private List<Medicion> mediciones;

	//Constructores
	
	public Gordi() {
		this.mediciones = new ArrayList<>();
	}

	//Métodos
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public List<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	public void setDatos() {
		super.setDatos();
		
		this.altura=UtilidadesPanel.leerFloat("Introduce la altura");
		//Genero el código
		this.codigo=this.getNombre().substring(0, 2).toUpperCase()+ "-" + Integer.toString(getFecNacimiento().getYear()).substring(2, 4);	
	
		//Pido las mediciones, por lo menos 1
		setMediciones();
	}
	
	public void setMediciones() {
		//Pido las mediciones, por lo menos 1
		boolean mas=true;
		Medicion nueva;
		
		do {
			nueva = new Medicion();
			nueva.setDatos();
			mediciones.add(nueva);
			mas=UtilidadesPanel.esBoolean("¿Quieres introducir más mediciones: ");
		}while(mas);
	}
	
	public void getMedicionesListado() {
		//Pido las mediciones, por lo menos 1

		for(Medicion pm:mediciones) {
			System.out.println(pm.getFecha()+"   "+pm.getPeso());
		}
	}
	//ArithmeticException: Java will not throw an exception if you divide by float zero. It will detect a run-time 
	//error only if you divide by integer zero not double zero.
    //If you divide by 0.0, the result will be INFINITY
	/*
	public float calcularIMC() {
		float imc=0;
		try {
			imc=mediciones.get(mediciones.size()-1).getPeso()/(altura*altura);	
		}catch(ArithmeticException e) {
			System.out.println("Error: División por cero"+e.getMessage());
		}
		return imc;
	}
	
	public float calcularIMC(float peso) {
		float imc=0;
		try {
			imc=peso/(altura*altura);	
		}catch(ArithmeticException e) {
			System.out.println("Error: División por cero"+e.getMessage());
		}
		return imc;
	}
	*/
	public float calcularIMC() {
		float imc=0;
		
		if (altura==0) {
			imc=-1;
		}else {
			imc=mediciones.get(mediciones.size()-1).getPeso()/(altura*altura);	
		}
		
//		try {
//			imc=mediciones.get(mediciones.size()-1).getPeso()/(altura*altura);	
//			
//			if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY)
//				throw new ArithmeticException();
//		}catch(ArithmeticException e) {
//			System.out.println("Error: División por cero ");
//		}
		return imc;
	}
	
	public float calcularIMC(float peso) {
		float imc=0;
		try {
			imc=peso/(altura*altura);	
			
			if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY)
				throw new ArithmeticException();
		}catch(ArithmeticException e) {
			System.out.println("Error: División por cero"+e.getMessage());
		}
		return imc;
	}
	
	public float obtenerUltimoPeso() {
		return mediciones.get(mediciones.size()-1).getPeso();
	}
	
	public float obtenerPrimerPeso() {
		return mediciones.get(0).getPeso();
	}
	
	public void introducirNuevoPeso() {
		Medicion nueva = new Medicion();
		nueva.setDatos();
		mediciones.add(nueva);
	}
	
	public void obtenerFicha() {
		float imcAnt=0, imcAc=0, dif=0;
		System.out.println("Año nac.   Altura");
		System.out.println(super.getFecNacimiento().getYear()+"   "+this.altura);
		imcAnt=obtenerUltimoPeso();   //Guardo el último peso para calcular la diferencia
		System.out.println("Mediciones: ");
		getMedicionesListado();
		introducirNuevoPeso();
		imcAc=obtenerUltimoPeso(); 
		System.out.printf("IMC ACTUAL=%.2f %n", calcularIMC());
		dif=imcAc-imcAnt;
		System.out.printf("Diferencia peso última medición: %+.2f kg %n", dif);
		imcAnt=obtenerPrimerPeso(); 
		System.out.printf("Diferencia peso desde el inicio del tratamiento: %+.2f kg %n", (imcAc-imcAnt));
		if (dif>0.200) {
			System.out.println("REVISIÓN DE LA DIETA!!!!");
		}else {
			System.out.println("LA DIETA ASIGNADA ES CORRECTA");
		}
	}
		
	@Override
	public String toString() {
		super.toString();
		return "Gordi [codigo=" + codigo + ", altura=" + altura + ", mediciones=" + mediciones + "]";
	}
	
	
}
