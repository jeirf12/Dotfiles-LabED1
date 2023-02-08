package Negocio;

import java.sql.Date;
import java.util.ArrayList;

public class Periodico extends RecursoBibliografico {
	private TipoPublicacion tipo;
	private Date fecha;
	
	public Periodico(String nombre, ArrayList<Autor> autores, int anio, String ciudad, String pais, int numEjemplares,TipoPublicacion tipo, Date fecha) {
		super(nombre, autores, anio, ciudad, pais, numEjemplares);
		this.tipo = tipo;
		this.fecha = fecha;
	}

	public TipoPublicacion getTipo() { return this.tipo; }

	public void setTipo(TipoPublicacion tipo) { this.tipo = tipo; }

	public Date getFecha() { return this.fecha; }

	public void setFecha(Date fecha) { this.fecha = fecha; }

	public void mostrarRecurso() {
		this.mostrarRecurso("Periódico");
		System.out.println("\tTipo Publicacion: "+this.tipo);
		System.out.println("\tFecha: "+this.fecha.getTime());
	}
}
