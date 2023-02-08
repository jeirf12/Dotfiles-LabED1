package Negocio;

public class Institucion extends Autor {

	public Institucion(String nombre) {	super(nombre); }

	@Override
	public void mostrarAutor() { System.out.println("\tNombre Institucion: "+this.nombre); }
}
