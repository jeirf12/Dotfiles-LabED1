package Negocio;

import java.util.ArrayList;

public class Libro extends RecursoBibliografico {
	private int edicion;
	private String editorial;
	private String ISBN;
	
	public Libro(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares, int edicion, String editorial, String iSBN) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.edicion = edicion;
		this.editorial = editorial;
		this.ISBN = iSBN;
	}

	public int getEdicion() { return this.edicion; }

	public void setEdicion(int edicion) { this.edicion = edicion; }

	public String getEditorial() { return this.editorial; }

	public void setEditorial(String editorial) { this.editorial = editorial; }

	public String getISBN() { return ISBN; }

	public void setISBN(String iSBN) { ISBN = iSBN; }

	@Override
	public void mostrarRecurso() {
		this.mostrarRecurso("Libro");
		System.out.println("\tEdicion: "+this.edicion);
		System.out.println("\tEditoral: "+this.editorial);
		System.out.println("\tISBN: "+this.ISBN);
	}
}
