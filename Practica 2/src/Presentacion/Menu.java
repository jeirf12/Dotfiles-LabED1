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
		} while (this.atrOpcion!=this.atrOpcionSalir);
	}
	private void imprimirMenu() {
		Consola.escribirSaltarLinea(atrTitulo, false);
		Consola.escribirSaltarLinea(this.atrVectorOpciones, false);
		Consola.escribirSaltarLinea(atrOpcionSalir+".Para salir...", false);
	}
	private void leerOpcion() {
		this.atrOpcion=0;
		atrOpcion=Consola.leer("Ingrese la opción deseada: ",this.atrOpcion,false);
		if (atrOpcion<1 || atrOpcion>atrOpcionSalir) {
			Consola.escribirSaltarLinea("Opción invalida", false);
		}
	}
	public Menu(String parTitulo,String[] parVectoropciones) {
		this.atrTitulo=parTitulo;
		this.atrVectorOpciones=parVectoropciones;
		this.atrOpcionSalir=parVectoropciones.length+1;
		this.iterarMenu();
	}
	public abstract void procesarOpcion();
}
