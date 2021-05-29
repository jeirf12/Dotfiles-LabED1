package Negocio;

public class Lista <T>{
	private NodoLista<T> cabeza;
	private int tamanio;
	
	public Lista() {
		this.cabeza=null;
		this.tamanio=0;
	}

	public NodoLista<T> getCabeza() {
		return cabeza;
	}

	public void setCabeza(NodoLista<T> cabeza) {
		this.cabeza = cabeza;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	public void agregar(T elemento) {
		NodoLista<T> nuevo=new NodoLista<T>(elemento);
		if (esVacia()) {
			cabeza=nuevo;
		}else {
			agregar(cabeza,nuevo);
		}
		this.tamanio++;
	}
	public void agregar(T elemento,int coste) {
		NodoLista<T> nuevo=new NodoLista<T>(elemento,coste);
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
			if (!aux.getSiguiente().getDato().equals(dato)) {
				eliminar(aux.getSiguiente(), dato);
			}else {
				aux.setSiguiente(aux.getSiguiente().getSiguiente());
			}
		}
	}
	public boolean esVacia() {
		return cabeza==null;
	}
}
