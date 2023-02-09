package Logica;

public class Persona {
	private int id;
	private String nombre;
	private int edad;

	public Persona(int id, String nombre, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
	}

	public int getId() { return this.id; }

	public void setId(int id) { this.id = id; }

	public String getNombre() { return this.nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public int getEdad() { return this.edad; }

	public void setEdad(int edad) { this.edad = edad; }

	public String toString() { return "Identificación: "+this.id+"\nNombre: "+this.nombre+"\nEdad: "+this.edad; }
}
