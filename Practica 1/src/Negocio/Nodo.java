package Negocio;

public class Nodo<T> {
	private T dato;
	private Nodo<T> siguiente;

	public Nodo() { this.siguiente = null; }

	public Nodo(T p) { this.siguiente = null; this.dato = p; }

	public Nodo(T t, Nodo<T> siguiente) { this.siguiente = siguiente; this.dato = t; }

	public T getDato() { return this.dato; }

	public void setDato(T dato) { this.dato = dato; }

	public Nodo<T> getSiguiente() { return this.siguiente; }

	public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }
}
