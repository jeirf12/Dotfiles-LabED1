package Negocio;

public class NodoLista<T> {
	private T dato;
	private int coste;
	private NodoLista<T> siguiente;

	public NodoLista() {
		this.dato=null;
		this.coste=0;
		this.siguiente = null;
	}

	public NodoLista(T dato) {
		this.dato = dato;
		this.coste=0;
		this.siguiente = null;
	}

	public NodoLista(T dato, int coste) {
		this.dato = dato;
		this.coste=coste;
		this.siguiente = null;
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

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
}
