package Negocio;

public class NodoLista<T> {
	private T dato;
	private NodoLista<T> siguiente;

	public NodoLista() {
		this.dato=null;
		this.siguiente = null;
	}

	public NodoLista(T dato) {
		this.dato = dato;
		this.siguiente = null;
	}

	public NodoLista(T dato, NodoLista<T> siguiente) {
		this.dato = dato;
		this.siguiente = siguiente;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public NodoLista<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoLista<T> siguiente) {
		this.siguiente = siguiente;
	}
}
