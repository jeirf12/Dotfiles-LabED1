package Presentacion;

import Negocio.*;

public class Test {
	
	@SuppressWarnings("unused")
	public static <T> void main(String[] args){
		String [] opciones = { "Adicionar Nodo nuevo al arbol", "Mostrar Padre y hermanos del nodo", "Realizar Recorridos PostOrden, PreOrden, InOrden" };
		MenuPrincipal menu = new MenuConfigurar<T>("M E N U    P R I N C I P A L", opciones);
	}
}
