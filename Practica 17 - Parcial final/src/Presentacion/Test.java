package Presentacion;

import java.util.ArrayList;

import Negocio.*;

public class Test{
	public static GrafoBipartito<Integer> grafoNumerico=new GrafoBipartito<Integer>();
	public static GrafoBipartito<String> grafoLetras=new GrafoBipartito<String>();
	
	public static void main(String[] args) {
		llenarGrafos();
		grafoLetras.eliminarArista("A", "G");
		grafoLetras.mostrarGrafo();
		grafoLetras.mostrarMatrizGrafoBipartito();
		
		grafoNumerico.eliminarArista(1, 6);
		grafoNumerico.mostrarGrafo();
		grafoNumerico.mostrarMatrizGrafoBipartito();
	}
	public static void llenarGrafos() {
		int tipoGrafo1,tipoGrafo2,grupo;
		String[] linea=null,linea2=null;
		Archivo archivo=new Archivo();
		ArrayList<String> datos=archivo.leer("Datos.txt");
		for (int i = 0; i < datos.size(); i++) {
			linea=datos.get(i).split(",");
			tipoGrafo1=seleccionGrafo(linea[0]);
			if (linea.length>2) {
				tipoGrafo1=seleccionGrafo(linea[2]);
			}
			linea2=datos.get(i).split("-");
			tipoGrafo2=seleccionGrafo(linea2[0]);
			if (linea2.length>2) {
				tipoGrafo2=seleccionGrafo(linea2[1]);
			}
			if (linea.length>=2) {
				grupo=seleccionConjunto(linea[0]);
				for (int j = 1; j < linea.length; j++) {
					if (tipoGrafo1 == 0) {
						if (Consola.esNumerico(linea[j])) {
							grafoNumerico.agregarVertice(Integer.parseInt(linea[j]),grupo);
						}
					} else if (tipoGrafo1 == 1) {
						if (!Consola.esNumerico(linea[j])) {
							grafoLetras.agregarVertice(linea[j],grupo);
						}
					}
				}
			}else if (linea2.length==3) {
				llenarAristas(linea2, tipoGrafo2);
			}
		}
	}
	public static void llenarAristas(String[] linea,int tipoGrafo) {
		if (tipoGrafo == 0) {
			grafoNumerico.agregarArista(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]),Integer.parseInt(linea[2]));
			
		} else if (tipoGrafo == 1) {
			grafoLetras.agregarArista(linea[0], linea[1],Integer.parseInt(linea[2]));
		}
	}
	public static int seleccionGrafo(String dato) {
		if (dato.equals("0") || (Consola.esNumerico(dato) && !dato.equals("1"))) {//Si el dato es 0 es un arbol numerico
			return 0; 
		}else if (dato.equals("1") || !Consola.esNumerico(dato)) {//Si el dato es 1 es un arbol caracter
			return 1;
		}
		return -1;//No pertenece a ninguno de los dos grafos
	}
	public static int seleccionConjunto(String dato) {
		if (dato.equals("G1")) {
			return 1;
		}else if (dato.equals("G2")) {
			return 2;
		}
		return -1;
	}
}
