package Negocio;

public class Lista <T>{
	private NodoLista<T> cabeza;
	private int tamanio;
	
	public Lista() { this.cabeza = null; this.tamanio = 0; }

	public NodoLista<T> getCabeza() { return this.cabeza; }

	public void setCabeza(NodoLista<T> cabeza) { this.cabeza = cabeza; }

	public int getTamanio() { return this.tamanio; }

	public void setTamanio(int tamanio) { this.tamanio = tamanio; }

	public void agregar(T elemento) {
		NodoLista<T> nuevo = new NodoLista<T>(elemento);
		if (this.esVacia()) this.cabeza = nuevo;
		else this.agregar(this.cabeza).setSiguiente(nuevo);
		this.tamanio++;
	}
	public void agregar(T elemento, int coste) {
		NodoLista<T> nuevo = new NodoLista<T>(elemento, coste);
		if (this.esVacia()) this.cabeza = nuevo;
		else this.agregar(this.cabeza).setSiguiente(nuevo);;
		this.tamanio++;
	}

	private NodoLista<T> agregar(NodoLista<T> aux) { return aux.getSiguiente() != null ? agregar(aux.getSiguiente()) : aux; }

	public void eliminar(T dato) {
		if (cabeza.getDato().equals(dato)) this.cabeza = this.cabeza.getSiguiente();
		else this.eliminar(this.cabeza, dato);
		this.tamanio--;
	}

	private void eliminar(NodoLista<T> aux, T dato) {
		if (aux.getSiguiente() != null) {
			if (!aux.getSiguiente().getDato().equals(dato)) this.eliminar(aux.getSiguiente(), dato);
			else aux.setSiguiente(aux.getSiguiente().getSiguiente());
		}
	}

	public boolean esVacia() { return this.cabeza == null; }
}
