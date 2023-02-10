package Negocio;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consola {

	public static <T> boolean escribirSaltarLinea (T parEtiqueta) {
		try { System.out.println(parEtiqueta); return true; } 
		catch (Exception e) { return false; }
	}

	public static <T> boolean escribir(T parEtiqueta) {
		try { System.out.print(parEtiqueta); return true; } 
		catch (Exception e) { return false; }
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T leer(T parVarlectura) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String valor = "", opcion = "";
		try {
			valor = br.readLine();
			opcion = ((Object) parVarlectura).getClass().getSimpleName();
			switch(opcion) {
				case "String" -> { if(!esNumerico(valor)) parVarlectura = (T) String.valueOf(valor.trim()); }
				case "Double" -> { if(esNumerico(valor) && valor.contains(".")) parVarlectura = (T) Double.valueOf(valor); }
				case "Float" -> { if(esNumerico(valor) && valor.contains(".")) parVarlectura = (T) Float.valueOf(valor); }
				case "Integer" -> { if(esNumerico(valor)) parVarlectura = (T) Integer.valueOf(valor); }
			}
		} catch (Exception e) { }
		return parVarlectura;
	}
	
	public static <T> T leer(String parEtiqueta, T parVariable) {
		T resultado;
		do {
			escribir(parEtiqueta);
			resultado = leer(parVariable);
		} while(resultado.equals(parVariable));
		return resultado;
	}
	
	public static <T> boolean leer(T [] parVector) {
		for (int varIndice = 0; varIndice < parVector.length; varIndice++) leer(parVector[varIndice]);
		return true;
	}

	public static <T> boolean leer(T [] parVector, int parCapacidadVector) {
		for (int varIndice = 0; varIndice < parCapacidadVector; varIndice++) leer(parVector[varIndice]);
		return true;
	}

	public static <T> boolean escribirSaltarLinea(T [] parVector) {
		for (int varIndice = 0; varIndice < parVector.length; varIndice++) {
			if (!escribirSaltarLinea((varIndice + 1) + "." + parVector[varIndice])) {
				System.out.println("Existe un dato incorrecto del vector que no se puede mostrar");
				System.out.println(varIndice+"posicion del vector con el error");
			}
		}
		return true;
	}

	public static boolean esNumerico(String dato) { return dato.matches("[+-]?\\d*(\\.\\d+)?"); }
}
