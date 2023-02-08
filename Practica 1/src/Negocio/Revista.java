package Negocio;

import java.util.ArrayList;

public class Revista extends RecursoBibliografico{
	private String nomRevista;
	private int volumen;
	private String ISSN;
	private String url;
	
	public Revista(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares, String nomRev, int volumen, String iSSN, String url) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.nomRevista = nomRev;
		this.volumen = volumen;
		this.ISSN = iSSN;
		this.url = url;
	}

	public String getNomRevista() { return this.nomRevista; }

	public void setNomRevista(String nombre) { this.nomRevista = nombre; }

	public int getVolumen() { return this.volumen; }

	public void setVolumen(int volumen) { this.volumen = volumen; }

	public String getISSN() { return this.ISSN; }

	public void setISSN(String iSSN) { this.ISSN = iSSN; }

	public String getUrl() { return this.url; }

	public void setUrl(String url) { this.url = url; }

	public void mostrarRecurso() {
		this.mostrarRecurso("Revista");
		System.out.println("\tNombre Revista: "+this.nomRevista);
		System.out.println("\tVolumen: "+this.volumen);
		System.out.println("\tISSN: "+this.ISSN);
		System.out.println("\tUrl: "+this.url);
	}
}
