package Negocio;

public class Lista<T> {
	private NodoLista<T> cabeza;
	private int tamanio;

	public void agregar(T dato) {
		NodoLista<T> nuevo=new NodoLista<T>(dato);
		if (esVacia()) {
			cabeza=nuevo;
		}else {
			agregar(cabeza,nuevo);
		}
		this.tamanio++;
	}
	private void agregar(NodoLista<T> aux,NodoLista<T> nuevo) {
		if (aux.getSiguiente()!=null) {
			agregar(aux.getSiguiente(),nuevo);
		}else {
			aux.setSiguiente(nuevo);
		}
	}

	public boolean esVacia() {
		return cabeza == null;
	}

	public int getTamanio() {
		return tamanio;
	}

	public NodoLista<T> getCabeza() {
		return cabeza;
	}
	
	public void eliminar(T dato) {
		if (cabeza.getDato().equals(dato)) {
			cabeza=cabeza.getSiguiente();
		}else {
			eliminar(cabeza, dato);
		}
		this.tamanio--;
	}
	private void eliminar(NodoLista<T> aux,T dato) {
		if (aux.getSiguiente()!=null) {
			if (aux.getSiguiente().getDato().equals(dato)) {
				aux.setSiguiente(aux.getSiguiente().getSiguiente());
			}else {
				eliminar(aux.getSiguiente(), dato);
			}
		}
	}
}
