package Negocio;

import java.util.ArrayList;

public class Monografia  extends RecursoBibliografico{
	private String programa;
	private String departamento;
	private String universidad;
	public Monografia(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares,
			String programa, String departamento, String universidad) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.programa = programa;
		this.departamento = departamento;
		this.universidad = universidad;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getUniversidad() {
		return universidad;
	}
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	public void mostrarRecurso() {
		System.out.println("Tipo Monografia");
		System.out.println("Nombre: "+nombre);
		for (int i = 0; i < autores.size(); i++) {
			System.out.println("Autor "+(i+1)+":");
			autores.get(i).mostrarAutor();
		}
		System.out.println("Año: "+anio);
		System.out.println("Ciudad: "+ciudad);
		System.out.println("País: "+pais);
		System.out.println("N° Ejemplares: "+numEjemplares);
		System.out.println("Programa: "+programa);
		System.out.println("Departamento: "+departamento);
		System.out.println("Universidad: "+universidad);
	}
}
