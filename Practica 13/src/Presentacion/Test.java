package Presentacion;

import java.util.ArrayList;

import Negocio.Arbol;
import Negocio.Archivo;

public class Test {
	public static void main(String[] args) {
		Arbol<Integer> arb=new Arbol<Integer>();
		llenarArbol(arb);
		arb.imprimirArbolNiveles();
		System.out.println("Longitud de camino del arbol");
		arb.longitudCamino();
		System.out.println("Mostrar Arbol en zigzag");
		arb.imprimirZigZag();
	}
	public static void llenarArbol(Arbol<Integer> arbol) {
		Archivo arch=new Archivo();
		ArrayList<String> lineaArc=new ArrayList<>();
		String[] linea=null;
		lineaArc=arch.leer("Datos.txt");
		for (int i = 0; i < lineaArc.size(); i++) {
			linea=lineaArc.get(i).split(",");
			for (int j = 0; j < linea.length; j++) {
				if (esNumerico(linea[j])) {
					arbol.insertar(Integer.parseInt(linea[j]));
				}
			}
		}
	}
	// Valida cuando un dato es tipo numerico o cadena
	public static boolean esNumerico(String Dato) {
		return Dato.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
