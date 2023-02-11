package Presentacion;

import java.util.ArrayList;
import Negocio.*;

public class Test {
	private static Grafo<String> grafoLetras;
	private static Grafo<Integer> grafoNumerico;

	public static void main(String[] args) {
		llenarVerticesGrafo();
		if (grafoLetras.getListaVertices().getCabeza() != null) {
			grafoLetras.verticesGrafoPonderado();
			grafoLetras.methodKruskal();
		}
		if (grafoNumerico.getListaVertices().getCabeza() != null) {
			grafoNumerico.verticesGrafoPonderado();
			grafoNumerico.methodKruskal();
		}
	}

	private static void llenarVerticesGrafo() {
		grafoLetras = new Grafo<String>();
		grafoNumerico = new Grafo<Integer>();
		int tipoGrafo = 0;
		String[] linea = null, linea2 = null;
		Archivo arch = new Archivo("Datos.txt");
		ArrayList<String> datos = arch.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			linea = datos.get(i).split(",");
			linea2 = linea[0].split("-");
			if (linea.length == 1 && linea2.length == 1) tipoGrafo = seleccionGrafo(linea[0]);
			if (linea.length != 1) {
				for (int j = 0; j < linea.length; j++) {
					if (tipoGrafo == 0 && esNumerico(linea[j])) grafoNumerico.agregarVertice(Integer.parseInt(linea[j])); 
					else if (tipoGrafo == 1 && !esNumerico(linea[j])) grafoLetras.agregarVertice(linea[j]);
				}
			} else if (linea2.length != 1) llenarAristas(linea2, tipoGrafo);
		}
	}

	private static void llenarAristas(String[] linea, int tipoGrafo) {
		if (tipoGrafo == 0) grafoNumerico.agregarArista(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]), Integer.parseInt(linea[2]));
		else if (tipoGrafo == 1) grafoLetras.agregarArista(linea[0], linea[1], Integer.parseInt(linea[2]));
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
