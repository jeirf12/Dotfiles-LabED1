package Negocio;

import java.util.ArrayList;

public class Monografia  extends RecursoBibliografico{
	private String programa;
	private String departamento;
	private String universidad;

	public Monografia(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares, String programa, String departamento, String universidad) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.programa = programa;
		this.departamento = departamento;
		this.universidad = universidad;
	}

	public String getPrograma() { return this.programa; }

	public void setPrograma(String programa) { this.programa = programa; }

	public String getDepartamento() { return this.departamento; }

	public void setDepartamento(String departamento) { this.departamento = departamento; }

	public String getUniversidad() { return this.universidad; }

	public void setUniversidad(String universidad) { this.universidad = universidad; }

	public void mostrarRecurso() {
		this.mostrarRecurso("Monografía");
		System.out.println("\tPrograma: "+this.programa);
		System.out.println("\tDepartamento: "+this.departamento);
		System.out.println("\tUniversidad: "+this.universidad);
	}
}
