package Negocio;

public class Lista<T> {
	private NodoLista<T> cabeza;
	private int tamanio;

	public void agregar(T dato) {
		NodoLista<T> nuevo = new NodoLista<T>(dato);
		if (!this.esVacia()) this.agregar(this.cabeza).setSiguiente(nuevo);
		else this.cabeza = nuevo;
		this.tamanio++;
	}

	private NodoLista<T> agregar(NodoLista<T> aux) { return aux.getSiguiente() != null ? this.agregar(aux.getSiguiente()): aux; }

	public void agregar(T dato, int valor) {
		NodoLista<T> nuevo = new NodoLista<T>(dato, valor);
		if (!this.esVacia()) this.agregar(this.cabeza).setSiguiente(nuevo);
		else this.cabeza = nuevo;
		this.tamanio++;
	}

	public boolean esVacia() { return this.cabeza == null; }

	public int getTamanio() { return this.tamanio; }

	public NodoLista<T> getCabeza() { return this.cabeza; }

	public int indexOf(T dato) {
		int contador = -1;
		if (!this.esVacia()) contador = indexOf(this.cabeza, dato, -1);
		return contador;
	}

	private int indexOf(NodoLista<T> aux, T dato, int contador) {
		if (aux != null) {
			if (dato.equals(aux.getDato())) return contador;
			contador = indexOf(aux.getSiguiente(), dato, contador + 1);
		}
		return contador;
	}

	public NodoLista<T> devolverNodo(int posicion) {
		NodoLista<T> aux = cabeza;
		int contador = 0;
		if (posicion < 0 || posicion >= getTamanio()) System.out.println("La posicion insertada no es correcta");
		else aux = this.devolverNodo(this.cabeza, contador, posicion);
		return aux;

	}

	private NodoLista<T> devolverNodo(NodoLista<T> aux, int contador, int posicion){
		if (aux != null) {
			if (posicion == contador) return aux;
			aux = this.devolverNodo(aux.getSiguiente(), contador + 1, posicion);
		}
		return aux;
	}

	public void eliminar(T dato) {
		if (cabeza.getDato().equals(dato)) this.cabeza = this.cabeza.getSiguiente();
		else this.eliminar(this.cabeza, dato);
		this.tamanio--;
	}

	private void eliminar(NodoLista<T> aux, T dato) {
		if (aux.getSiguiente() != null) {
			if (aux.getSiguiente().getDato().equals(dato)) aux.setSiguiente(aux.getSiguiente().getSiguiente());
			else this.eliminar(aux.getSiguiente(), dato);
		}
	}
}
