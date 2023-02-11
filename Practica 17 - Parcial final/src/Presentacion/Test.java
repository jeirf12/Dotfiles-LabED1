package Presentacion;

import java.util.ArrayList;

import Negocio.*;

public class Test{
	public static GrafoBipartito<Integer> grafoNumerico;
	public static GrafoBipartito<String> grafoLetras;
	
	public static void main(String[] args) {
		llenarGrafos();
		grafoLetras.eliminarArista("A", "G");
		grafoLetras.mostrarGrafo();
		grafoLetras.mostrarMatrizGrafoBipartito();
		
		grafoNumerico.eliminarArista(1, 6);
		grafoNumerico.mostrarGrafo();
		grafoNumerico.mostrarMatrizGrafoBipartito();
	}

	private static void llenarGrafos() {
		grafoNumerico = new GrafoBipartito<Integer>();
		grafoLetras = new GrafoBipartito<String>();
		int tipoGrafo = 0, grupo;
		String[] linea = null, linea2 = null;
		Archivo archivo = new Archivo("Datos.txt");
		ArrayList<String> datos = archivo.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			linea = datos.get(i).split(",");
			linea2 = linea[0].split("-");
			if (linea.length == 1 && linea2.length == 1) tipoGrafo = seleccionGrafo(linea[0]);
			if (linea.length != 1) {
				grupo = seleccionConjunto(linea[0]);
				for (int j = 1; j < linea.length; j++) {
					if (tipoGrafo == 0 && Consola.esNumerico(linea[j])) grafoNumerico.agregarVertice(Integer.parseInt(linea[j]),grupo);
					else if (tipoGrafo == 1 && !Consola.esNumerico(linea[j])) grafoLetras.agregarVertice(linea[j],grupo);
				}
			} else if (linea2.length != 1) llenarAristas(linea2, tipoGrafo);
		}
	}

	private static void llenarAristas(String[] linea,int tipoGrafo) {
		if (tipoGrafo == 0) grafoNumerico.agregarArista(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]),Integer.parseInt(linea[2]));
			
		else if (tipoGrafo == 1) grafoLetras.agregarArista(linea[0], linea[1],Integer.parseInt(linea[2]));
	}

	private static int seleccionGrafo(String dato) {
		//Si el dato es 0 es un arbol numerico
		if (dato.equals("0") || (Consola.esNumerico(dato) && !dato.equals("1"))) return 0; 
		//Si el dato es 1 es un arbol caracter
		else if (dato.equals("1") || !Consola.esNumerico(dato)) return 1;
		//No pertenece a ninguno de los dos grafos
		return -1;
	}

	public static int seleccionConjunto(String dato) {
		if (dato.equals("G1")) return 1;
		else if (dato.equals("G2")) return 2;
		return -1;
	}
}
