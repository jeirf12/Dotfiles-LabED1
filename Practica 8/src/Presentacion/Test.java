package Presentacion;

import java.util.ArrayList;
import Negocio.*;

public class Test {
	private static Arbol<Integer> arbolNumerico = new Arbol<Integer>();

	public static void main(String[] args) {
		llenarArbol();
		arbolNumerico.imprimirArbolNiveles();
		System.out.println("\nArbol podado");
		arbolNumerico.podar(arbolNumerico.retornaNivelPodar());
		arbolNumerico.imprimirArbolNiveles();
		System.out.println("\nSerie fibonnaci");
		arbolNumerico.mostrarFibonacci();
	}
	
	private static boolean esNumerico(String Dato) { return Dato.matches("[+-]?\\d*(\\.\\d+)?"); }
	
	private static void llenarArbol() {
		Archivo arch = new Archivo("datos.txt");
		ArrayList<String> lineaArc = arch.getDatos();
		String[] linea = null;
		for (int i = 0; i < lineaArc.size(); i++) {
			linea = lineaArc.get(i).split(",");
			for (int j = 0; j < linea.length; j++) if (esNumerico(linea[j])) arbolNumerico.insertar(Integer.parseInt(linea[j]));
		}
	}
}
