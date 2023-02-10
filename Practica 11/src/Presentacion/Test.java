package Presentacion;

import java.util.ArrayList;

import Negocio.*;

public class Test {
	private static ArbolN_Ario<Integer> arbolNumerico;
	private static ArbolN_Ario<String> arbolCaracter;
	
	public static void main(String[] args) {
		llenarArbol();
		if (arbolCaracter.getRaiz() != null) {
			arbolCaracter.imprimirNivelesArbol();
			arbolCaracter.contarHijos();
			arbolCaracter.alturaArbol();
			arbolCaracter.mostrarHijosNodo("s");
			arbolCaracter.mostrarNodosNivel(1);
		}
		if (arbolNumerico.getRaiz() != null) {
			arbolNumerico.imprimirNivelesArbol();
			arbolNumerico.contarHijos();
			arbolNumerico.alturaArbol();
			arbolNumerico.mostrarHijosNodo(18);
			arbolNumerico.mostrarNodosNivel(1);
		}
	}

	private static void llenarArbol() {
		arbolNumerico = new ArbolN_Ario<Integer>();
		arbolCaracter = new ArbolN_Ario<String>();
		int opcionArbol = 0;
		String [] lineaDatos = null;
		Archivo arch = new Archivo("Datos.txt");
		ArrayList<String> datos = arch.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			lineaDatos = datos.get(i).split("-");
			if(lineaDatos.length != 2) opcionArbol = seleccionArbol(lineaDatos[0]);
			if (opcionArbol == 1) {
				if (esNumerico(lineaDatos[0]) && !lineaDatos[0].equals("1")) {
					if (arbolNumerico.getRaiz() == null) arbolNumerico.insertarRaiz(Integer.parseInt(lineaDatos[0]));
					else if (esNumerico(lineaDatos[1])) arbolNumerico.agregar(Integer.parseInt(lineaDatos[0]), Integer.parseInt(lineaDatos[1]));
				}
			} else if(opcionArbol == 0){
				if (!esNumerico(lineaDatos[0])) {
					if (arbolCaracter.getRaiz() == null) arbolCaracter.insertarRaiz(lineaDatos[0]);
					else if (!esNumerico(lineaDatos[1])) arbolCaracter.agregar(lineaDatos[0], lineaDatos[1]);
				}
			}
		}
	}

	private static int seleccionArbol(String dato) {
		//Si el dato es 1 es un arbol numerico
		if (dato.equals("1") || (esNumerico(dato) && !dato.equals("0"))) return 1; 
		//Si el dato es 0 es un arbol caracter
		else if (dato.equals("0") || !esNumerico(dato)) return 0;
		//No pertenece a ninguno de los dos arboles
		return -1;
	}

	private static boolean esNumerico(String Dato) { return Dato.matches("[+-]?\\d*(\\.\\d+)?"); }
}
