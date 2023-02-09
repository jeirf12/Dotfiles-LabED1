package Presentacion;

import Logica.Consola;

public abstract class Menu {
	private String atrTitulo;
	private String [] atrVectorOpciones;
	protected int atrOpcion;
	private int atrOpcionSalir;
	
	private void iterarMenu() {
		do {
			this.imprimirMenu();
			this.leerOpcion();
			this.procesarOpcion();
		} while (this.atrOpcion != this.atrOpcionSalir);
	}

	private void imprimirMenu() {
		Consola.escribirSaltarLinea(this.atrTitulo);
		Consola.escribirSaltarLinea(this.atrVectorOpciones);
		Consola.escribirSaltarLinea(atrOpcionSalir + ".Para salir...");
	}

	private void leerOpcion() {
		this.atrOpcion = 0;
		this.atrOpcion = Consola.leer("Ingrese la opción deseada: ", this.atrOpcion);
		if (this.atrOpcion < 1 || this.atrOpcion > this.atrOpcionSalir) Consola.escribirSaltarLinea("Opción invalida");
	}

	public Menu(String parTitulo, String[] parVectoropciones) {
		this.atrTitulo = parTitulo;
		this.atrVectorOpciones = parVectoropciones;
		this.atrOpcionSalir = parVectoropciones.length + 1;
		this.iterarMenu();
	}

	public abstract void procesarOpcion();
}
