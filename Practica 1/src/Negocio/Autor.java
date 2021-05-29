package Negocio;

public abstract class Autor {
	protected String nombre;

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public abstract void mostrarAutor();
}
