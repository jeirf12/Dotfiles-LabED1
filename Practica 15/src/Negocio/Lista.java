package Negocio;

public class Lista<T> {
	private NodoLista<T> cabeza;
	private int tamanio;

	public void agregar(T dato) {
		NodoLista<T> nuevo = new NodoLista<T>(dato);
		if (this.esVacia()) this.cabeza = nuevo;
		else this.agregar(cabeza).setSiguiente(nuevo);;
		this.tamanio++;
	}

	private NodoLista<T> agregar(NodoLista<T> aux) { return aux.getSiguiente() != null ? this.agregar(aux.getSiguiente()) : aux; }

	public boolean esVacia() { return this.cabeza == null; }

	public int getTamanio() { return this.tamanio; }

	public NodoLista<T> getCabeza() { return this.cabeza; }
	
	public void eliminar(T dato) {
		if (this.cabeza.getDato().equals(dato)) this.cabeza = this.cabeza.getSiguiente();
		else {
			NodoLista<T> aux = eliminar(this.cabeza, dato);
			aux.setSiguiente(aux.getSiguiente().getSiguiente());
		}
		this.tamanio--;
	}

	private NodoLista<T> eliminar(NodoLista<T> aux,T dato) {
		if (aux.getSiguiente() != null) {
			if (aux.getSiguiente().getDato().equals(dato)) return aux;
			return this.eliminar(aux.getSiguiente(), dato);
		}
		return aux;
	}
}
