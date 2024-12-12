package clases;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import utilidades.Utilidades;

public class Gordi extends Persona implements Comparable<Gordi>{
	
	//Atributos
	private String codigo;
	private float altura;
	private TreeMap<LocalDate,Medicion> mediciones;

	//Constructores
	
	public Gordi() {
		this.mediciones = new TreeMap<LocalDate, Medicion>();
	}

	//M�todos
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

	public Map<LocalDate, Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(TreeMap<LocalDate, Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	public void setDatos() {
		super.setDatos();
		
		this.altura=Utilidades.leerFloat("Introduce la altura");
		//Genero el c�digo
		this.codigo=getNombre().substring(0, 2).toUpperCase()+ "-" + Integer.toString(getFecNacimiento().getYear()).substring(2, 4);	
	
		//Se muestra el código
		System.out.println("El código del Gordi es: "+ codigo);
		
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
			mediciones.put(nueva.getFecha(),nueva);
			mas=Utilidades.esBoolean("�Quieres introducir m�s mediciones: ");
		}while(mas);
	}
	
	public void getMedicionesListado() {
		//Pido las mediciones, por lo menos 1

		for(Medicion pm:mediciones.values()) {
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
			System.out.println("Error: Divisi�n por cero"+e.getMessage());
		}
		return imc;
	}
	
	public float calcularIMC(float peso) {
		float imc=0;
		try {
			imc=peso/(altura*altura);	
		}catch(ArithmeticException e) {
			System.out.println("Error: Divisi�n por cero"+e.getMessage());
		}
		return imc;
	}
	*/
	public float calcularIMC() {
		float imc=0;
		
//		if (altura==0) {
//			imc=-1;
//		}else {
//			imc=mediciones.get(mediciones.size()-1).getPeso()/(altura*altura);	
//		}
		
		try {
			Map.Entry<LocalDate, Medicion> ultima = mediciones.lastEntry();
			imc=ultima.getValue().getPeso()/(altura*altura);	
			
//			if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY)
//				throw new ArithmeticException();
		}catch(ArithmeticException e) {
			System.out.println("Error: Divisi�n por cero ");
			return -1;
		}
		return imc;
	}
	
	public float calcularIMC(float peso) {
		float imc=0;
		try {
			imc=peso/(altura*altura);	
			
			if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY)
				throw new ArithmeticException();
		}catch(ArithmeticException e) {
			System.out.println("Error: Divisi�n por cero"+e.getMessage());
		}
		return imc;
	}
	
	public float obtenerUltimoPeso() {
		Map.Entry<LocalDate, Medicion> ultimo = mediciones.lastEntry();
		return ultimo.getValue().getPeso()/(altura*altura);	
	}
	
	public float obtenerPrimerPeso() {
		Map.Entry<LocalDate, Medicion> primero = mediciones.firstEntry();
		return primero.getValue().getPeso()/(altura*altura);	
	}
	
	public void introducirNuevoPeso() {
		Medicion nueva = new Medicion();
		nueva.setDatos();
		mediciones.put(nueva.getFecha(),nueva);
	}
	
	public void obtenerFicha() {
		float pesoAnt=0, pesoAc=0, dif=0;
		System.out.println("A�o nac.   Altura");
		System.out.println(getFecNacimiento().getYear()+"   "+this.altura);
		pesoAnt=obtenerUltimoPeso();   //Guardo el �ltimo peso para calcular la diferencia
		System.out.println("Mediciones: ");
		getMedicionesListado();
		introducirNuevoPeso();
		pesoAc=obtenerUltimoPeso(); 
		System.out.printf("IMC ACTUAL=%.2f %n", calcularIMC());
		dif=pesoAc-pesoAnt;
		System.out.printf("Diferencia peso �ltima medici�n: %+.2f kg %n", dif);
		pesoAnt=obtenerPrimerPeso(); 
		System.out.printf("Diferencia peso desde el inicio del tratamiento: %+.2f kg %n", (pesoAc-pesoAnt));
		if (dif>0.200) {
			System.out.println("REVISI�N DE LA DIETA!!!!");
		}else {
			System.out.println("LA DIETA ASIGNADA ES CORRECTA");
		}
	}
		
	@Override
	public String toString() {
		super.toString();
		return "Gordi [codigo=" + codigo + ", altura=" + altura + ", mediciones=" + mediciones + "]";
	}

	@Override
//	public int compareTo(Gordi otroGordi) {
//		
//		return super.getNombre().compareTo(otroGordi.getNombre());
//	}
	
	public int compareTo(Gordi otroGordi) {
		
		return Float.compare(altura, otroGordi.getAltura());
	}
}
