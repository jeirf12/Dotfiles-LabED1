package Negocio;

public class NodoGrafo<T> {
	private Lista<NodoGrafo<T>> listaNodos;
	private T elemento;

	public NodoGrafo(T elemento) { this.listaNodos = new Lista<NodoGrafo<T>>(); this.elemento = elemento; }

	public Lista<NodoGrafo<T>> getListaNodos() { return this.listaNodos; }

	public void setListaNodos(NodoGrafo<T> valor) { this.listaNodos.agregar(valor); }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }

	public void eliminarArista(NodoGrafo<T> nodo) { this.listaNodos.eliminar(nodo); }
}
