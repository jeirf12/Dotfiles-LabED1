package Negocio;

public class Consola {
	public static <T> boolean escribirSaltarLinea (T parEtiqueta) {
		try {
			System.out.println(parEtiqueta);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static <T> boolean escribir(T parEtiqueta) {
		try {
			System.out.print(parEtiqueta);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean esNumerico(String dato) {
		return dato.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
