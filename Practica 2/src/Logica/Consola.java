package Logica;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consola {

	public static <T> boolean escribirSaltarLinea(T mensaje) {
		try {
			System.out.println(mensaje);
			return true;
		} catch (Exception e) { return false; }
	}

	public static <T> boolean escribir(T mensaje) {
		try {
			System.out.print(mensaje);
			return true;
		} catch (Exception e) { return false; }
	}
	
	private static <T> T leer(T parRead) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String value = "", option = "";
		try {
			value = input.readLine();
			option =  ((Object) parRead).getClass().getSimpleName();
			switch(option){
				case "String" -> { if(!isNumber(value)) parRead = (T) String.valueOf(value.trim()); }
				case "Double" -> { if(isNumber(value) && value.contains(".")) parRead = (T) Double.valueOf(value); }
				case "Float" -> { if(isNumber(value) && value.contains(".")) parRead = (T) Float.valueOf(value); }
				case "Integer" -> { if(isNumber(value)) parRead = (T) Integer.valueOf(value); }
			}
		} catch (Exception e) {}
		return parRead;
	}
	
	public static <T> T leer(String parEtiqueta, T parVariable) {
		T resultado;
		do {
			escribir(parEtiqueta);
			resultado = leer(parVariable);
		} while(parVariable.equals(resultado));
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
			if (escribirSaltarLinea((varIndice + 1) + "." + parVector[varIndice]) == false) {
				escribirSaltarLinea("Existe un dato incorrecto del vector que no se puede mostrar");
				escribirSaltarLinea(varIndice + " posicion del vector con el error");
			}
		}
		return true;
	}

	public static boolean isNumber(String chain) { return chain.matches("[+-]?\\d*(\\.\\d+)?"); }
}
