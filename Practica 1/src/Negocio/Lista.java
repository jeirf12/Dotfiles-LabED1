package Negocio;

public class Lista<T> {
	private Nodo<T> cabeza;
	private int tamanio;

	public void agregar(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (this.esVacia()) cabeza = nuevo;
		else this.recorrerLista(cabeza).setSiguiente(nuevo);
		++this.tamanio;
	}

	private Nodo<T> recorrerLista(Nodo<T> aux) {
		if (aux.getSiguiente() != null) return this.recorrerLista(aux.getSiguiente());
		return aux;
	}

	private Nodo<T> obtenerNodoPosicion(int posicion) { return this.obtenerNodoPosicion(cabeza, posicion, 0); }

	public T obtenerElementoPosicion(int posicion) { 
		Nodo<T> nodo = this.obtenerNodoPosicion(posicion);
		if(nodo != null) return nodo.getDato();
		return null;
	}

	private Nodo<T> obtenerNodoPosicion(Nodo<T> nodo, int posicion, int contador){
		if (nodo != null && contador != posicion) return this.obtenerNodoPosicion(nodo.getSiguiente(), posicion, contador + 1);
		return nodo != null && contador == posicion ? nodo : null;
	}
	
	//permite eliminar un elemento por su dato
	public boolean eliminarElemento(T dato) {
		boolean validaEliminacion = false;
		if (!this.esVacia()) {
			if (cabeza.getDato().equals(dato)) { cabeza = cabeza.getSiguiente(); validaEliminacion = true; }
			else validaEliminacion = eliminarElemento(cabeza, dato);
		}
		if (validaEliminacion) --this.tamanio;
		return validaEliminacion;
	}

	private boolean eliminarElemento(Nodo<T> aux,T dato) {
		if (aux.getSiguiente() != null) {
			if (!aux.getSiguiente().getDato().equals(dato)) return eliminarElemento(aux.getSiguiente(), dato);
			aux.setSiguiente(aux.getSiguiente().getSiguiente());
			return true;
		}
		return false;
		
	}

	//permite eliminar un elemento por su posicion
	public T eliminarElementoPosicion(int posicion) {
		T dato = null;
		if (!this.esVacia()) {
			dato = this.obtenerElementoPosicion(posicion);
			if (this.eliminarElemento(dato)) return dato;
		}
		return dato;
	}

	public void editarPorPosicion(int posicion, T valor) {
		if (posicion >= 0 && posicion < this.tamanio) {
			Nodo<T> nodo = this.obtenerNodoPosicion(posicion);
			nodo.setDato(valor);
		}
	}

	public void borrarLista() { this.cabeza=null; this.tamanio=0; }

	public int getTamanio() { return this.tamanio; }

	//permite saber si esta vacia la lista
	public boolean esVacia() { return this.cabeza == null; }

	public Nodo<T> getCabeza() { return this.cabeza; }
 }
