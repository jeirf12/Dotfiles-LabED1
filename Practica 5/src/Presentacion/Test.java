package Presentacion;

import Negocio.*;

public class Test {
	
	@SuppressWarnings("unused")
	public static <T> void main(String[] args){
		String [] opciones= {"1.Adicionar Nodo nuevo al arbol","2.Mostrar Padre y hermanos del nodo","3.Realizar Recorridos PostOrden, PreOrden, InOrden"};
		MenuPrincipal menu=new MenuConfigurar<T>("M E N U    P R I N C I P A L", opciones);
	}
}
