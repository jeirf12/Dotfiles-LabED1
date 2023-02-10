package Presentacion;

import java.util.ArrayList;
import Negocio.*;

public class Test {

	public static void main(String[] args) {
		Arbol<Integer> arb = new Arbol<Integer>();
		llenarArbol(arb);
		arb.imprimirArbol();
		System.out.println("Longitud de camino del arbol");
		arb.longitudCamino();
		System.out.println("Mostrar Arbol en zigzag");
		arb.imprimirZigZag();
	}

	private static void llenarArbol(Arbol<Integer> arbol) {
		Archivo arch = new Archivo("Datos.txt");
		ArrayList<String> lineaArc = arch.getDatos();
		String[] linea = null;
		for (int i = 0; i < lineaArc.size(); i++) {
			linea = lineaArc.get(i).split(",");
			for (int j = 0; j < linea.length; j++) if (esNumerico(linea[j])) arbol.insertar(Integer.parseInt(linea[j]));
		}
	}

	// Valida cuando un dato es tipo numerico o cadena
	private static boolean esNumerico(String Dato) { return Dato.matches("[+-]?\\d*(\\.\\d+)?"); }
}
