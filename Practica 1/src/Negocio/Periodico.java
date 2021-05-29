package Negocio;

import java.sql.Date;
import java.util.ArrayList;

public class Periodico extends RecursoBibliografico{
	private TipoPublicacion tipo;
	private Date fecha;
	
	public Periodico(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares,TipoPublicacion tipo, Date fecha) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.tipo = tipo;
		this.fecha = fecha;
	}
	public TipoPublicacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoPublicacion tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void mostrarRecurso() {
		System.out.println("Tipo Periodico");
		System.out.println("Nombre: "+nombre);
		for (int i = 0; i < autores.size(); i++) {
			System.out.println("Autor "+(i+1)+":");
			autores.get(i).mostrarAutor();
		}
		System.out.println("Año: "+anio);
		System.out.println("Ciudad: "+ciudad);
		System.out.println("País: "+pais);
		System.out.println("N° Ejemplares: "+numEjemplares);
		System.out.println("Tipo Publicacion: "+tipo);
		System.out.println("Fecha: "+fecha.getTime());
	}
}
