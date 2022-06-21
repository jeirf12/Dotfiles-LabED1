package Logica;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consola {
	public static <T> boolean escribirSaltarLinea (T parEtiqueta,boolean parWichMessage) {
		try {
			System.out.println(parEtiqueta);
			if (parWichMessage) {
				System.out.println("Se escribio correctamente");
			}
			return true;
		} catch (Exception e) {
			if (parWichMessage) {
				System.out.println("no se pudo escribir correctamente debido a: "+e.getMessage());
			}
			return false;
		}
	}
	public static <T> boolean escribir(T parEtiqueta,boolean parWichMessage) {
		try {
			System.out.print(parEtiqueta);
			if (parWichMessage) {
				System.out.print("Se escribio correctamente");
			}
			return true;
		} catch (Exception e) {
			if (parWichMessage) {
				System.out.print("no se pudo escribir correctamente debido a: "+e.getMessage());
			}
			return false;
		}
	}
	
	private static <T> T leer(T parRead, boolean parWichMessage) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String value = "", option = "";
		T parBackupRead = parRead;
		try {
			value = input.readLine();
			option =  ((Object) parRead).getClass().getSimpleName();
			switch(option){
				case "String" -> { if(!isNumber("" + parRead)) parRead = (T) String.valueOf(value.trim()); }
				case "Double" -> { if(isNumber("" + parRead) && value.contains(".")) parRead = (T) Double.valueOf(value); }
				case "Float" -> { if(isNumber("" + parRead) && value.contains(".")) parRead = (T) Float.valueOf(value); }
				case "Integer" -> { if(isNumber("" + parRead)) parRead = (T) Integer.valueOf(value); }
			}
			if (parWichMessage) {
				System.out.print("Se leyo correctamente");
			}
		} catch (Exception e) {
			if (parWichMessage) {
				System.out.println("No se pudo leer debido a el error: "+e.getMessage());
			}
		}
		if(parBackupRead.equals(parRead)) escribir("Value introduced no valid", parWichMessage);
		return parRead;
	}
	
	public static <T> T leer(String parEtiqueta,T parVariable,boolean parWichMessage) {
		escribir(parEtiqueta, parWichMessage);
		return leer(parVariable, parWichMessage);
	}
	public static <T> boolean leer(T [] parVector) {
		for (int varIndice = 0; varIndice < parVector.length; varIndice++) {
			leer(parVector[varIndice], false);
		}
		return true;
	}
	public static <T> boolean leer(T [] parVector,int parCapacidadVector) {
		for (int varIndice = 0; varIndice < parCapacidadVector; varIndice++) {
			leer(parVector[varIndice],false);
		}
		System.out.println("se leyo correctamente el vector");
		return true;
	}
	public static <T> boolean escribirSaltarLinea(T [] parVector,boolean parMensaje) {
		for (int varIndice = 0; varIndice < parVector.length; varIndice++) {
			if (escribirSaltarLinea(parVector[varIndice], parMensaje)==false) {
				System.out.println("Existe un dato incorrecto del vector que no se puede mostrar");
				System.out.println(varIndice+"posicion del vector con el error");
			}
		}
		return true;
	}
	public static boolean isNumber(String chain) {
		return chain.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
