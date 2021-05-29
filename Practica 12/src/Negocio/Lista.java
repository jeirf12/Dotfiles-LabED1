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
	
	public void agregar(T dato){
		NodoLista<T> nuevo=new NodoLista<>(dato);
		if (cabeza==null) {
			cabeza=nuevo;
		}else {
			agregar(cabeza,nuevo);
		}
		this.tamanio++;
	}
	
	private void agregar(NodoLista<T> aux,NodoLista<T> nuevo){
		if (aux.getSiguiente()!=null) {
			agregar(aux.getSiguiente(),nuevo);
		}else {
			aux.setSiguiente(nuevo);
		}
	}
}
