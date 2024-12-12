package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import clases.Gordi;
import clases.Persona;
import clases.Pesador;
import utilidades.Estadistica;
import utilidades.Utilidades;


public class ControlPeso {

	// Menua
	private static int menu() {
		int resp;
		System.out.println("\n1. ALTA Gordi/Pesador");
		System.out.println("2. LISTADO de Gordis");
		System.out.println("3. A�ADIR una medici�n a un Gordi y VER su evoluci�n: ");
		System.out.println("4. ESTAD�STICA de Gordis");
		System.out.println("5. Visualizar Gordis por orden alfabético");
		System.out.println("6. Salir");
		resp = Utilidades.leerInt(1, 6, "ELIJA UNA OPCI�N");
		return resp;
	}

	public static void main(String[] args) {
		// Menu
		// Lista de elementos no repetidos y ordenados por orden de inserci�n
		Map<String, Persona> personas = new HashMap<>();
		

		// Hasierako menua bistaratuko dugu
		int opc;

		do {
			opc = menu();
			if (personas.isEmpty() && opc > 1 && opc < 5) {
				System.out.println("No se han introducido personas");
			} else {
				switch (opc) {
				case 1:
					alta(personas);
					break;
				case 2:
					listadoGordis(personas);
					break;
				case 3:
					anadirGordis(personas);
					break;
				case 4:
					estadistica(personas);
					break;
				case 5:
					listarGordisOrdenados(personas);
					break;
				}
			}
		} while (opc != 5);
	}

	private static void listarGordisOrdenados(Map<String, Persona> personas) {
		Map<String, Gordi> gordis = new TreeMap<String, Gordi>();
		
		for(Persona per: personas.values()) {
			if (per instanceof Gordi) {
				gordis.put(per.getNombre(), (Gordi) per);
			}	
		}
		
		//
		System.out.println("Gordis por nombre");
		Iterator<String> claveI = gordis.keySet().iterator();
		while(claveI.hasNext()) {
			System.out.println(gordis.get(claveI.next()));
		}
		
//		for(Persona per:personas.values()) {
//			if(per instanceof Gordi) {
//				gordis.put(per.getNombre(), per);
//			}
//		}
		
//		Collections.sort(gordis);
//		for(Gordi gor:gordis) {
//			System.out.println(gor);
//		}
//		
		
	}

	private static void estadistica(Map<String, Persona> personas) {
		float imc;
		Estadistica aux;
		int num;
		boolean algunGordi = false;

		List<Estadistica> estadisticas = new ArrayList<>();
		Persona[] personasArray = new Persona[personas.size()];
		ArrayList<Persona> personasAL = new ArrayList<>(personas.values());
		personasAL.toArray(personasArray);

		// Primero rellenamos la tabla con la estad�stica
		Estadistica nueva = new Estadistica(0, (float) 15.99, "Infrapeso:Delgadez Severa", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica(16, (float) 16.99, "Infrapeso:Delgadez moderada", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica(17, (float) 18.49, "Infrapeso:Delgadez aceptable", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica((float) 18.50, (float) 24.99, "Peso Normal", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica((float) 25.50, (float) 29.99, "Sobrepeso", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica((float) 30.00, (float) 34.99, "Obeso: Tipo I", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica((float) 35.00, (float) 40.00, "Obeso: Tipo II", 0);
		estadisticas.add(nueva);
		nueva = new Estadistica((float) 40.00, Float.MAX_VALUE, "Obeso: Tipo II", 0);
		estadisticas.add(nueva);

		// Recorro el fichero de personas buscando los gordis y calculando el IMC
		for (int i = 0; i < personasArray.length; i++) {
			if (personasArray[i] instanceof Gordi) {
				for (Estadistica e : estadisticas) { // Recorro las estad�sticas en busca del rango que corresponda
					imc = ((Gordi) personasArray[i]).calcularIMC(((Gordi) personasArray[i]).obtenerPrimerPeso()); // Calculamos
																							// peso
					if (e.getIndiceInf() <= imc && imc <= e.getIndiceSup()) {
						e.setCuantos(e.getCuantos() + 1);
						algunGordi = true;
						break;
					}
				}
			}
		}

		if (algunGordi) {
			// Ordenamos el array por n�mero de Gordis
			for (int i = 0; i < estadisticas.size(); i++) {
				for (int j = i + 1; j < estadisticas.size(); j++) {
					if (estadisticas.get(i).getCuantos() < estadisticas.get(j).getCuantos()) {
						aux = estadisticas.get(i);
						estadisticas.set(i, estadisticas.get(j));
						estadisticas.set(j, aux);
					}
				}
			}

			// Visualizamos el listado
			System.out.println("Clasificaci�n            N� de gordis");
			for (Estadistica e : estadisticas) {
				System.out.println(e.getClasificacion() + "\t" + e.getCuantos());
			}
		} else {
			System.out.println("No tenemos Gordis para mostrar");
		}

	}

	private static void anadirGordis(Map<String, Persona> personas) {
		//
		String nombre = Utilidades.introducirCadena("Introduce el nombre de gordi:");
		
		if(personas.containsKey(nombre)) {
			if ((personas.get(nombre) instanceof Gordi)) {
				((Gordi) personas.get(nombre)).obtenerFicha();
			}else {
				System.out.println("Esta persona no es un Gordi");
			}
		}else{
			System.out.println("El gordi buscado no ha sido encontrado");
		}

	}

	private static void listadoGordis(Map<String, Persona> personas) {
		//
		boolean haygordis = false;
		int num;
		float imc;
		Persona per;

		
		Iterator<Persona> it = personas.values().iterator();
		while(it.hasNext()) {
			per = it.next();
			if (per instanceof Gordi) {
				if (!haygordis) {
					System.out.println("*************LISTADO DE GORDIS**************");
					System.out.println("NOMBRE      EDAD    C�DIGO    IMC");
					haygordis = true;
				}
				// Si me da divisi�n por 0 no quiero que me salga Infinity
				imc = ((Gordi) per).calcularIMC();
//					if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY) {
				if (imc == -1) {
					System.out.printf("%s    %d     %s      \n", per.getNombre(), per.calcularEdad(),
							((Gordi) per).getCodigo());
				} else {
					System.out.printf("%s \t %d     %s      %.2f \n", per.getNombre(), per.calcularEdad(),
							((Gordi) per).getCodigo(), imc);
				}

			}
		}
		
//		for (Persona per : personas.values()) {
//			if (per instanceof Gordi) {
//				if (!haygordis) {
//					System.out.println("*************LISTADO DE GORDIS**************");
//					System.out.println("NOMBRE      EDAD    C�DIGO    IMC");
//					haygordis = true;
//				}
//				// Si me da divisi�n por 0 no quiero que me salga Infinity
//				imc = ((Gordi) per).calcularIMC();
////					if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY) {
//				if (imc == -1) {
//					System.out.printf("%s    %d     %s      \n", per.getNombre(), per.calcularEdad(),
//							((Gordi) per).getCodigo());
//				} else {
//					System.out.printf("%s \t %d     %s      %.2f \n", per.getNombre(), per.calcularEdad(),
//							((Gordi) per).getCodigo(), imc);
//				}
//
//			}
//		}
		if (!haygordis) {
			System.out.println("No hay Gordis");
		}

	}

	private static void alta(Map<String, Persona> personas) {
		//
		char tipo;
		boolean mas = true;
		Persona nueva;

		do {
			char[] caracteres = { 'G', 'P' };
			tipo = Utilidades.leerCharArray(caracteres,
					"Si quieres introducir una Gordi introduce G si quieres introducir un Pesador P: ");

			if (tipo == 'g' || tipo == 'G') {
				nueva = new Gordi();
			} else {
				nueva = new Pesador();
			}
		
			nueva.setDatos();
			
			personas.put(nueva.getNombre(), nueva);

		  
			mas = Utilidades.esBoolean("�Quieres introducir otra persona?");
		} while (mas);
	}
}
