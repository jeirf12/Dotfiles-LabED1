package Logica;

public class Nodo<T> {
	private T dato;
	private Nodo<T> siguiente;

	public Nodo() { this.siguiente = null; }

	public Nodo(T dato) { this.siguiente = null; this.dato = dato; }

	public Nodo(T dato, Nodo<T> siguiente) { this.siguiente = siguiente; this.dato = dato; }

	public T getDato() { return this.dato; }

	public void setDato(T dato) { this.dato = dato; }

	public Nodo<T> getSiguiente() { return this.siguiente; }

	public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }
}
