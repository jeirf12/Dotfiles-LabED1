package Negocio;

public class NodoArbol <T> {
	private T elemento;
	private Lista<NodoArbol<T>> hijos;
	
	public NodoArbol() { this.elemento = null; this.hijos = new Lista<NodoArbol<T>>(); }

	public NodoArbol(T elemento) { this.elemento = elemento; this.hijos = new Lista<NodoArbol<T>>(); }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }

	public Lista<NodoArbol<T>> getHijos() { return this.hijos; }

	public void setHijos(NodoArbol<T> hijo) { this.hijos.agregar(hijo); }
}
