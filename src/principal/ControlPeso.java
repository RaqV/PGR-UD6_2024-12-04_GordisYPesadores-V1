package principal;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import clases.Gordi;
import clases.Persona;
import clases.Pesador;
import utilidades.Estadistica;
import utilidades.UtilidadesPanel;

public class ControlPeso {
	
	//Menua
	private static int menu() {
		int resp;
		System.out.println("\n1. ALTA Gordi/Pesador");
		System.out.println("2. LISTADO de Gordis");
		System.out.println("3. A�ADIR una medici�n a un Gordi y VER su evoluci�n: ");
		System.out.println("4. ESTAD�STICA de Gordis");
		System.out.println("5. Salir");
		resp = UtilidadesPanel.leerInt(1, 5, "ELIJA UNA OPCI�N");
		return resp;
	}
	
	public static void main(String[] args) {
		//Menu
		//Lista de elementos no repetidos y ordenados por orden de inserci�n
		List<Persona> personas = new ArrayList<>();

		//Hasierako menua bistaratuko dugu
		int opc; 
		
		do {
			opc= menu();
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
			}
		} while (opc != 5);
	}

	private static void estadistica(List<Persona> personas) {
		float imc;
		Estadistica aux;
		int num;
		boolean algunGordi=false;
		
		if(!personas.isEmpty()) {
			List <Estadistica> estadisticas=new ArrayList<>();
			Persona[] personasArray = new Persona[personas.size()];
			personas.toArray(personasArray);
			
			//Primero rellenamos la tabla con la estad�stica
			Estadistica nueva =new Estadistica(0, (float) 15.99,"Infrapeso:Delgadez Severa", 0);
		 	estadisticas.add(nueva);
			nueva =new Estadistica(16, (float) 16.99,"Infrapeso:Delgadez moderada", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica(17, (float) 18.49,"Infrapeso:Delgadez aceptable", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica((float)18.50, (float)24.99,"Peso Normal", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica((float)25.50, (float)29.99,"Sobrepeso", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica((float)30.00, (float)34.99,"Obeso: Tipo I", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica((float)35.00, (float)40.00,"Obeso: Tipo II", 0);
			estadisticas.add(nueva);
			nueva =new Estadistica((float)40.00, Float.MAX_VALUE,"Obeso: Tipo II", 0);
			estadisticas.add(nueva);
			
			//Recorro el fichero de personas buscando los gordis y calculando el IMC
			for(int i=0;i<personasArray.length;i++) {
				if(personasArray[i] instanceof Gordi) {
					for(Estadistica e:estadisticas) { 		//Recorro las estad�sticas en busca del rango que corresponda
						imc=((Gordi)personasArray[i]).calcularIMC(((Gordi)personasArray[i]).obtenerPrimerPeso());   //Calculamos IMC con el primer peso
						if(e.getIndiceInf()<= imc && imc<=e.getIndiceSup()){
							e.setCuantos(e.getCuantos()+1);
							algunGordi=true;
							break;
						}
					}
				}	
			}
			
			if (algunGordi) {
				//Ordenamos el array por n�mero de Gordis
				for(int i=0;i<estadisticas.size();i++) {
					for(int j=i+1;j<estadisticas.size();j++) {
						if (estadisticas.get(i).getCuantos()<estadisticas.get(j).getCuantos()) {
							aux=estadisticas.get(i);
							estadisticas.set(i, estadisticas.get(j));
							estadisticas.set(j, aux);
						}
					}
				}
				
				//Visualizamos el listado
				System.out.println("Clasificaci�n            N� de gordis");
				for(Estadistica e:estadisticas) {
					System.out.println(e.getClasificacion()+ "\t"+ e.getCuantos());
				}
			}else{
				System.out.println("No tenemos Gordis para mostrar");
			}
			
		}else {
			System.out.println("No se ha introducido ninguna persona");
		}
	}
	
	private static void anadirGordis(List<Persona> personas) {
		// 
		boolean encontrado=false;
		int num;
		
		String nombre=UtilidadesPanel.introducirCadena("Introduce el nombre de gordi:");
		
		if(!personas.isEmpty()) {
			for(Persona per: personas) {
				if((per instanceof Gordi && (per.getNombre().compareToIgnoreCase(nombre)==0))) {
						encontrado=true;
						((Gordi)per).obtenerFicha();
				}		
			}
			if(!encontrado) {
				System.out.println("El gordi buscado no ha sido encontrado");
			}	
		}else {
			System.out.println("No se han introducido personas");
		}	
	}

	private static void listadoGordis(List<Persona> personas) {
		// 
		boolean haygordis=false;
		int num;
		float imc;
		
		if(!personas.isEmpty()) {
			for(Persona per: personas) {
				if(per instanceof Gordi) {
					if(!haygordis) {
						System.out.println("*************LISTADO DE GORDIS**************");
						System.out.println("NOMBRE      EDAD    C�DIGO    IMC");
					}
					//Si me da divisi�n por 0 no quiero que me salga Infinity
					imc=((Gordi) per).calcularIMC();  
//					if (imc == Float.POSITIVE_INFINITY || imc== Float.NEGATIVE_INFINITY) {
					if (imc==-1) {
						System.out.printf("%s    %d     %s      \n", per.getNombre(), per.calcularEdad(), ((Gordi) per).getCodigo());
					}else {
						System.out.printf("%s    %d     %s      %.2f \n", per.getNombre(), per.calcularEdad(), ((Gordi) per).getCodigo(), imc);
					}
				
				}
			}			
			if(!haygordis) {
				System.out.println("No hay Gordis");
			}
		}else {
			System.out.println("No hay personas");
		}
	}

	private static void alta(List<Persona> personas) {
		// 
		char tipo;
		boolean mas=true;
		Persona nueva;
		
		
		do{
			char[] caracteres= {'G', 'P'};
			tipo = UtilidadesPanel.leerChar(caracteres, "Si quieres introducir una Gordi introduce G si quieres introducir un Pesador P: ");
					
			if (tipo == 'g'|| tipo == 'G') {
				nueva=new Gordi();
			}else{
				nueva=new Pesador();
			}
			nueva.setDatos();
			personas.add(nueva);
			
			if (tipo == 'g'|| tipo == 'G') {
				System.out.println("El c�digo del Gordi es: "+((Gordi)nueva).getCodigo());
			}
		
			mas = UtilidadesPanel.esBoolean("�Quieres introducir otra persona?");
		}while (mas);			
	}
}
