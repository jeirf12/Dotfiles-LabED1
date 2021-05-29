package Negocio;

public class Nodo<T> {
	private T dato;
	private Nodo<T> siguiente;

	public Nodo() {
		siguiente = null;
	}
	public Nodo(T p) {
		siguiente = null;
		dato = p;
	}

	public Nodo(T t, Nodo<T> siguiente) {
		this.siguiente = siguiente;
		dato = t;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}
