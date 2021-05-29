package Test;

import java.util.ArrayList;

import Negocio.*;

public class Test {
	private static ArbolN_Ario<String> arbolCaracter=new ArbolN_Ario<String>();
	private static ArbolN_Ario<Integer> arbolNumerico=new ArbolN_Ario<Integer>();
	
	public static void main(String[] args) {
		llenarArbol();
		if (arbolNumerico.getRaiz()!=null) {
			System.out.println("Arbol Numerico");
			arbolNumerico.imprimirNivelesArbol();
			arbolNumerico.padreNodo(18);
			arbolNumerico.nivelNodo(18);
			arbolNumerico.mostrarHermanos(18);
		}
		if (arbolCaracter.getRaiz()!=null) {
			System.out.println("Arbol caracter");
			arbolCaracter.imprimirNivelesArbol();
			arbolCaracter.padreNodo("s");
			arbolCaracter.nivelNodo("s");
			arbolCaracter.mostrarHermanos("s");
		}
	}
	public static void llenarArbol() {
		try {
			int opcionArbol;
			String [] lineaDatos=null;
			ArrayList<String> datos=new ArrayList<String>();
			Archivo arch=new Archivo();
			datos=arch.leer("Datos.txt");
			for (int i = 0; i < datos.size(); i++) {
				lineaDatos=datos.get(i).split("-");
				opcionArbol=seleccionArbol(lineaDatos[0]);
				if (opcionArbol==1) {
					if (esNumerico(lineaDatos[0]) && !lineaDatos[0].equals("1")) {
						if (arbolNumerico.getRaiz()==null) {
							arbolNumerico.insertarRaiz(Integer.parseInt(lineaDatos[0]));
						}else {
							if (esNumerico(lineaDatos[1])) {
								arbolNumerico.agregar(Integer.parseInt(lineaDatos[0]), Integer.parseInt(lineaDatos[1]));
							}
						}
					}
				} else if(opcionArbol==0){
					if (!esNumerico(lineaDatos[0])) {
						if (arbolCaracter.getRaiz()==null) {
							arbolCaracter.insertarRaiz(lineaDatos[0]);
						} else {
							if (!esNumerico(lineaDatos[1])) {
								arbolCaracter.agregar(lineaDatos[0], lineaDatos[1]);
							}
						}
						
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("error: "+ex.getMessage());
		}
	}
	public static int seleccionArbol(String dato) {
		if (dato.equals("1") || (esNumerico(dato) && !dato.equals("0"))) {//Si el dato es 1 es un arbol numerico
			return 1; 
		}else if (dato.equals("0") || !esNumerico(dato)) {//Si el dato es 0 es un arbol caracter
			return 0;
		}
		return -1;//No pertenece a ninguno de los dos arboles
	}
	public static boolean esNumerico(String Dato) {
        return Dato.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
