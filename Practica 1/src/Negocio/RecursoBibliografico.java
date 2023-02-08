package Negocio;

import java.util.ArrayList;

public abstract class RecursoBibliografico {
	protected String nombre;
	protected ArrayList<Autor>autores; 
	protected int anio;
	protected String ciudad;
	protected String pais;
	protected int numEjemplares;

	public RecursoBibliografico(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares) {
		this.nombre = nombre;
		this.autores = autores;
		this.anio = anio;
		this.ciudad = ciudad;
		this.pais = pais;
		this.numEjemplares = numEjemplares;
	}

	public String getNombre() { return this.nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public ArrayList<Autor> getAutores() { return this.autores; }

	public void setAutores(ArrayList<Autor> autores) { this.autores = autores; }

	public int getAnio() { return this.anio; }

	public void setAnio(int anio) { this.anio = anio; }

	public String getCiudad() { return this.ciudad; }

	public void setCiudad(String ciudad) { this.ciudad = ciudad; }

	public String getPais() { return this.pais; }

	public void setPais(String pais) { this.pais = pais; }

	public int getNumEjemplares() { return this.numEjemplares; }

	public void setNumEjemplares(int numEjemplares) { this.numEjemplares = numEjemplares; }

	protected void mostrarRecurso(String tipo) {
		System.out.println("\n#----------------------------------------------------#");
		System.out.println("Tipo " + tipo);
		System.out.println("\tNombre: "+this.nombre);
		for (int i = 0; i < this.autores.size(); i++) {
			System.out.println("Autor #"+(i+1)+":");
			this.autores.get(i).mostrarAutor();
		}
		System.out.println("\tAño: "+this.anio);
		System.out.println("\tCiudad: "+this.ciudad);
		System.out.println("\tPaís: "+this.pais);
		System.out.println("\tN° Ejemplares: "+this.numEjemplares);
	}

	public abstract void mostrarRecurso();
}
