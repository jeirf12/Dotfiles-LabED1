package Presentacion;

import Negocio.*;

public class Test {

	public static void main(String[] args) {
		ArbolBinario<Integer> ObjetoArbol = new ArbolBinario<Integer>();
		
		ObjetoArbol.insertar(3);
		ObjetoArbol.insertar(3, 4, "I");
		ObjetoArbol.insertar(3, 1, "D");
		ObjetoArbol.insertar(4, 2, "I");
		ObjetoArbol.insertar(4, 5, "D");
		ObjetoArbol.insertar(1, 9, "I");
		ObjetoArbol.insertar(1, 8, "D");
		ObjetoArbol.eliminar(1, 8);

		//mostrar el �rbol resultante
		ObjetoArbol.mostrarArbolSangrado();
	}
}
