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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Autor> getAutores() {
		return autores;
	}
	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getNumEjemplares() {
		return numEjemplares;
	}
	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}

	public abstract void mostrarRecurso();
	
}
