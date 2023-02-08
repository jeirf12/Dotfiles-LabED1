package Negocio;

public class Persona extends Autor {
	private String apellidos;

	public Persona(String nombre, String apellidos) { super(nombre); this.apellidos = apellidos; }

	public String getApellidos() { return this.apellidos; }

	public void setApellidos(String apellidos) { this.apellidos = apellidos; }

	@Override
	public void mostrarAutor() {
		System.out.println("\tNombre Persona: "+this.nombre);
		System.out.println("\tApellido: "+this.apellidos);
	}
}
