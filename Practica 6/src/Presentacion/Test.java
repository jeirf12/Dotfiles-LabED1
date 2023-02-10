package Presentacion;

import java.util.ArrayList;
import java.util.Scanner;

import Negocio.Arbol;
import Negocio.Archivo;

public class Test {
	private static Scanner entrada = new Scanner(System.in);
	private static Arbol<String> arbolString;
	private static Arbol<Integer> arbolInt;

	public static void main(String[] args) {
		//Saca los datos del archivo y los ingresa al árbol ya sea de números o de caracteres
		llenarArbol(); 		
		//Imprime el menu de ejecución
		imprimirMenu();
	}
	
	private static void imprimirMenu() {
		int opcion;
		do {
			System.out.println("********Menu Principal********");
			System.out.println(" 1.Mostrar Recorridos del árbol\n 2.Mostrar nodos con respecto a un nivel\n 3.Mostrar Elemento mayor y menor a un nivel del árbol\n 4.Salir");
			System.out.println("Elige una opción:");
			opcion = entrada.nextInt();
			switch (opcion) {
				case 1:
					System.out.println("\nÁrbol de números");
					arbolInt.recorridos();
					arbolInt.imprimirArbolNiveles();
					System.out.println("\nÁrbol de caracteres");
					arbolString.recorridos();
					arbolString.imprimirArbolNiveles();
					break;

				case 2:
					System.out.println("\nÁrbol de números");
					for (int j = 0; j < arbolInt.niveles() + 1; j++) {
						System.out.println(" Nivel " + (j + 1) + "\n Información: ");
						System.out.print("\t");
						arbolInt.nodosNivel(j);
						System.out.println();
					}
					System.out.println("\nÁrbol de caracteres");
					for (int i = 0; i < arbolString.niveles() + 1; i++) {
						System.out.println(" Nivel " + (i + 1) + "\n Información: ");
						System.out.print("\t");
						arbolString.nodosNivel(i);
						System.out.println();
					}
					break;

				case 3:
					System.out.println("\nÁrbol de números");
					arbolInt.mayorElemento();
					arbolInt.menorElemento();
					System.out.println("\nÁrbol de caracteres");
					arbolString.mayorElemento();
					arbolString.menorElemento();
					break;

				case 4: System.out.println("Gracias por utilizar el programa!"); break;
			}
		} while (opcion < 1 || opcion != 4);
	}

	private static void llenarArbol() {
		Archivo arch = new Archivo("Datos.txt");
		arbolInt = new Arbol<Integer>();
		arbolString = new Arbol<String>();
		ArrayList<String> datos = arch.getDatos();
		int cont = 0;
		String [] linea = null;
		while(datos.size() > cont) {
			if (datos.get(cont).equalsIgnoreCase("0")) {
				linea = datos.get(cont + 1).split(",");
				for (int j = 0; j < linea.length; j++) if (esNumerico(linea[j])) arbolInt.insertar(Integer.parseInt(linea[j]));
			}
			else if(datos.get(cont).equalsIgnoreCase("1")) {
				linea = datos.get(cont + 1).split(",");
				for (int j = 0; j < linea.length; j++) if (!esNumerico(linea[j])) arbolString.insertar(linea[j]);
			}
			cont++;
		}
	}

	public static boolean esNumerico(String Dato) { return Dato.matches("[+-]?\\d*(\\.\\d+)?"); }
}
