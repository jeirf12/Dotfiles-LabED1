package Presentacion;

import java.util.ArrayList;

import Negocio.*;

public class Test {

	public static void main(String[] args) {
        Arbol<Integer> arbolNumerico=new Arbol<Integer>();
		llenarArbol(arbolNumerico);
		if (arbolNumerico.getRaiz()!=null) {
			arbolNumerico.imprimirArbol();
			System.out.println("******SERIE DE LUCAS PRIMOS******");
			System.out.println("1. Numeros de lucas primos");
			arbolNumerico.funcionLucasPrimo(10);
			System.out.println("2. Numeros de lucas primos Arbol");
			arbolNumerico.mostrarLucasArbol();
		}else {
			System.out.println("El arbol esta vacio");
		}
	}
	
	public static void llenarArbol(Arbol<Integer> arbol){
		ArrayList<String> datos=new ArrayList<String>();
		String[] linea=null;
		Archivo Arch=new Archivo();
		datos=Arch.leer("Datos.txt");
		for (int i = 0; i < datos.size(); i++) {
			linea=datos.get(i).split(",");
			for (int j = 0; j < linea.length; j++) {
				if (esNumerico(linea[j])) {
					arbol.insertar(Integer.parseInt(linea[j]));
				}
			}
		}
	}
	
	public static boolean esNumerico(String Dato) {
        return Dato.matches("[+-]?\\d*(\\.\\d+)?");
    }

}
