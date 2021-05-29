package Negocio;

import java.util.ArrayList;

public class Libro extends RecursoBibliografico{
	private int edicion;
	private String editorial;
	private String ISBN;
	
	public Libro(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares, int edicion, String editorial, String iSBN) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.edicion = edicion;
		this.editorial = editorial;
		ISBN = iSBN;
	}
	public int getEdicion() {
		return edicion;
	}
	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	@Override
	public void mostrarRecurso() {
		System.out.println("Tipo Libro");
		System.out.println("Nombre: "+nombre);
		for (int i = 0; i < autores.size(); i++) {
			System.out.println("Autor "+(i+1)+":");
			autores.get(i).mostrarAutor();
		}
		System.out.println("Año: "+anio);
		System.out.println("Ciudad: "+ciudad);
		System.out.println("País: "+pais);
		System.out.println("N° Ejemplares: "+numEjemplares);
		System.out.println("Edicion: "+edicion);
		System.out.println("Editoral: "+editorial);
		System.out.println("ISBN: "+ISBN);
	}
}
