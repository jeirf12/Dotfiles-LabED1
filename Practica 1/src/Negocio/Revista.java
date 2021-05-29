package Negocio;

import java.util.ArrayList;

public class Revista extends RecursoBibliografico{
	private String nomRevista;
	private int volumen;
	private String ISSN;
	private String url;
	
	public Revista(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares,
			String nomRev, int volumen, String iSSN, String url) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		nomRevista = nomRev;
		this.volumen = volumen;
		ISSN = iSSN;
		this.url = url;
	}
	public String getNomRevista() {
		return nomRevista;
	}
	public void setNomRevista(String nombre) {
		this.nomRevista = nombre;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public String getISSN() {
		return ISSN;
	}
	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void mostrarRecurso() {
		System.out.println("Tipo Revista");
		System.out.println("Nombre: "+nombre);
		for (int i = 0; i < autores.size(); i++) {
			System.out.println("Autor "+(i+1)+":");
			autores.get(i).mostrarAutor();
		}
		System.out.println("Año: "+anio);
		System.out.println("Ciudad: "+ciudad);
		System.out.println("País: "+pais);
		System.out.println("N° Ejemplares: "+numEjemplares);
		System.out.println("Nombre Revista: "+nomRevista);
		System.out.println("Volumen: "+volumen);
		System.out.println("ISSN: "+ISSN);
		System.out.println("Url: "+url);
	}
}
