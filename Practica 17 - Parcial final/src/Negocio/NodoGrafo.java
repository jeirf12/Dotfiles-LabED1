package Negocio;

public class NodoGrafo <T>{
	private Lista<NodoGrafo<T>> listaNodos;
	private T elemento;
	
	public NodoGrafo(T elemento) {
		this.listaNodos=new Lista<NodoGrafo<T>>();
		this.elemento=elemento;
	}

	public Lista<NodoGrafo<T>> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(NodoGrafo<T> elemento,int peso) {
		this.listaNodos.agregar(elemento,peso);
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	public void mostrarAristas() {
		NodoLista<NodoGrafo<T>> aux=this.listaNodos.getCabeza();
		mostrarAristas(aux);
		Consola.escribirSaltarLinea("");
	}
	private void mostrarAristas(NodoLista<NodoGrafo<T>> aux) {
		if (!esNulo(aux)) {
			Consola.escribir("["+aux.getDato().getElemento()+","+aux.getCoste()+"]");
			mostrarAristas(aux.getSiguiente());
		}
	}
	public void eliminarArista(NodoGrafo<T> vFinal) {
		this.listaNodos.eliminar(vFinal);
	}
	
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
}
