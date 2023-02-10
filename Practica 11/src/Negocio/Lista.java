package Negocio;

public class Lista <T>{
	private NodoLista<T> cabeza;
	private int tamanio;
	
	public Lista() { this.cabeza = null; this.tamanio = 0; }

	public NodoLista<T> getCabeza() { return this.cabeza; }

	public void setCabeza(NodoLista<T> cabeza) { this.cabeza = cabeza; }

	public int getTamanio() { return this.tamanio; }

	public void setTamanio(int tamanio) { this.tamanio = tamanio; }

	public void agregar(T dato) {
		NodoLista<T> nuevo = new NodoLista<>(dato);
		if (cabeza == null) this.cabeza = nuevo;
		else this.agregar(this.cabeza).setSiguiente(nuevo);;
		tamanio++;
	}

	private NodoLista<T> agregar(NodoLista<T> aux) { return aux.getSiguiente() != null ? this.agregar(aux.getSiguiente()) : aux; }
}
