package Negocio;

public class NodoLista <T>{
	private T dato;
	private int coste;
	private NodoLista<T> siguiente;
	
	public NodoLista() { this.dato = null; this.coste = 0; this.siguiente = null; }

	public NodoLista(T elemento) { this.dato = elemento; this.coste = 0; this.siguiente = null; }

	public NodoLista(T elemento, int coste) { this.dato = elemento; this.coste = coste; this.siguiente = null; }

	public T getDato() { return this.dato; }

	public void setDato(T elemento) { this.dato = elemento; }

	public int getCoste() { return this.coste; }

	public void setCoste(int coste) { this.coste = coste; }

	public NodoLista<T> getSiguiente() { return this.siguiente; }

	public void setSiguiente(NodoLista<T> siguiente) { this.siguiente = siguiente; }
}
