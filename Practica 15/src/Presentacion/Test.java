package Presentacion;

import java.util.ArrayList;
import Negocio.*;

public class Test {
	private static Grafo<Integer> grafoNumerico;
	private static Grafo<String> grafoLetras;
	
	public static void main(String[] args) {
		llenarVerticesGrafo();
		if (!grafoLetras.getListaVertices().esVacia()) grafoLetras.imprimirTodoGrafo();
		if (!grafoNumerico.getListaVertices().esVacia()) grafoNumerico.imprimirTodoGrafo();
	}
	
	private static void llenarVerticesGrafo() {
		grafoNumerico = new Grafo<Integer>();
		grafoLetras = new Grafo<String>();
		int tipoGrafo, tipoGrafo2;
		String[] linea = null, linea2 = null;
		Archivo arch = new Archivo("Datos.txt");
		ArrayList<String> datos = arch.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			linea = datos.get(i).split(" ");
			tipoGrafo = seleccionGrafo(linea[0]);
			if (linea.length >= 2) tipoGrafo = seleccionGrafo(linea[2]);
			linea2 = datos.get(i).split(",");
			tipoGrafo2 = seleccionGrafo(linea2[0]);
			if (linea2.length >= 2) tipoGrafo2=seleccionGrafo(linea2[1]);
			if (linea.length >= 2) {
				for (int j = 0; j < linea.length; j++) {
					if (tipoGrafo == 0) {
						if (esNumerico(linea[j])) grafoNumerico.agregarVertice(Integer.parseInt(linea[j]));
					} else if (tipoGrafo == 1) {
						if (!esNumerico(linea[j])) grafoLetras.agregarVertice(linea[j]);
					}
				}
			} else if (linea2.length == 2) llenarAristas(linea2, tipoGrafo2);
		}
	}

	private static void llenarAristas(String[] linea, int tipoGrafo) {
		if (tipoGrafo == 0) grafoNumerico.agregarArista(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]));
		else if (tipoGrafo == 1) grafoLetras.agregarArista(linea[0], linea[1]);
	}

	private static int seleccionGrafo(String dato) {
		//Si el dato es 0 es un arbol numerico
		if (dato.equals("0") || (esNumerico(dato) && !dato.equals("1"))) return 0; 
		//Si el dato es 1 es un arbol caracter
		else if (dato.equals("1") || !esNumerico(dato)) return 1;
		//No pertenece a ninguno de los dos grafos
		return -1;	
	}

	private static boolean esNumerico(String Dato) { return Dato.matches("[+-]?\\d*(\\.\\d+)?"); }
}
