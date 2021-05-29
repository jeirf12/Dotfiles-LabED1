package Presentacion;

import java.util.ArrayList;

import Negocio.*;

public class Test {
	private static Arbol<Integer> arbolEnteros=new Arbol<Integer>();
	private static Arbol<String> arbolCaracter=new Arbol<String>();
	
	public static void main(String[] args) {
		llenarArboles();
		
		System.out.println("Arbol de enteros");
		arbolEnteros.imprimirArbolNiveles();
		arbolEnteros.recorridos();
		arbolEnteros.pesoArbol();
		arbolEnteros.primoHermano(89,2);
		arbolEnteros.primoHermano(27);
		
		System.out.println("Arbol de caracteres");
		arbolCaracter.imprimirArbolNiveles();
		arbolCaracter.recorridos();
		arbolCaracter.pesoArbol();
		arbolCaracter.primoHermano("Y", "Z");
		arbolCaracter.primoHermano("C");
	}
	//Llena el arbol con los datos de un archivo (txt)
	public static void llenarArboles() {
		ArrayList<String> datos=new ArrayList<String>();
		Archivo arch=new Archivo();
		datos=arch.leer("Datos.txt");
		String [] linea=null;
		int cont=0;
		while (datos.size()>cont) {
			if (datos.get(cont).equalsIgnoreCase("0")) {
				linea=datos.get(cont+1).split(",");
				for (int i = 0; i < linea.length; i++) {
					if (esNumerico(linea[i])) {
						arbolEnteros.insertar(Integer.parseInt(linea[i]));
					}
				}
			}else if(datos.get(cont).equalsIgnoreCase("1")) {
				linea=datos.get(cont+1).split(",");
				for (int i = 0; i < linea.length; i++) {
					if (!esNumerico(linea[i])) {
						arbolCaracter.insertar(linea[i]);
					}
				}
			}
			cont++;
		}
	}
	//Valida cuando un dato es tipo numerico o cadena
	public static boolean esNumerico(String Dato) {
        return Dato.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
