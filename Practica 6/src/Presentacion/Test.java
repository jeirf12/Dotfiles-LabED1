package Presentacion;

import java.util.ArrayList;
import java.util.Scanner;

import Negocio.Arbol;
import Negocio.Archivo;

public class Test {
	private static Scanner entrada=new Scanner(System.in);
	private static Arbol<String> arbolString=new Arbol<String>();
	private static Arbol<Integer> arbolInt=new Arbol<Integer>();
	public static void main(String[] args) {
		llenarArbol();//Saca los datos del archivo y los ingresa al arbol ya sea de numeros o de caracteres
		imprimirMenu();//Imprime el menu de ejecucion
	}
	
	public static void imprimirMenu() {
		int opcion;
		do {
			System.out.println("********Menu Principal********");
			System.out.println(" 1.Mostrar Recorridos del Arbol\n 2.Mostrar nodos con respecto a un nivel\n 3.Mostrar Elemento mayor y menor a un nivel del arbol\n 4.Salir");
			System.out.println("Elige una opcion:");
			opcion = entrada.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Arbol de numeros");
				arbolInt.recorridos();
				arbolInt.imprimirArbolNiveles();
				System.out.println("Arbol de caracteres");
				arbolString.recorridos();
				arbolString.imprimirArbolNiveles();
				break;
			case 2:
				
				System.out.println("Arbol de numeros");
				for (int j = 0; j < arbolInt.Niveles()+1; j++) {
					System.out.println(" Nivel "+(j+1)+"\n Informacion: ");
					arbolInt.nodosNivel(j);
				}
				
				System.out.println("Arbol de caracteres");
				for (int i = 0; i < arbolString.Niveles()+1; i++) {
					System.out.println(" Nivel "+(i+1)+"\n Informacion: ");
					arbolString.nodosNivel(i);
				}
				break;
			case 3:
				
				System.out.println("Arbol de numeros");
				arbolInt.mayorElemento();
				arbolInt.menorElemento();
				
				System.out.println("Arbol de caracteres");
				arbolString.mayorElemento();
				arbolString.menorElemento();
				
				break;
			case 4:
				System.out.println("Gracias por utilizar el programa!");
				break;
			}
		} while (opcion<1 || opcion!=4);
	}
	public static void llenarArbol() {
		Archivo arch=new Archivo();
		ArrayList<String> datos= new ArrayList<String>();
		datos=arch.leerArchivo("Datos.txt");
		int cont=0;
		String [] linea=null;
		while(datos.size()>cont) {
			if (datos.get(cont).equalsIgnoreCase("0")) {
				linea=datos.get(cont+1).split(",");
				for (int j = 0; j < linea.length; j++) {
					if (esNumerico(linea[j])) {
						arbolInt.insertar(Integer.parseInt(linea[j]));
					}
				}
			}
			else if(datos.get(cont).equalsIgnoreCase("1")) {
				linea=datos.get(cont+1).split(",");
				for (int j = 0; j < linea.length; j++) {
					if (!esNumerico(linea[j])) {
						arbolString.insertar(linea[j]);
					}
				}
			}
			cont++;
		}
	}
	public static boolean esNumerico(String Dato) {
        return Dato.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
