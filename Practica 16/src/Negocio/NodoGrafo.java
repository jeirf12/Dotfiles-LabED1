package Negocio;


public class NodoGrafo<T> {
	private Lista<NodoGrafo<T>> listaNodos;
	private T elemento;

	public NodoGrafo(T elemento) { this.listaNodos = new Lista<NodoGrafo<T>>(); this.elemento = elemento; }

	public Lista<NodoGrafo<T>> getListaNodos() { return this.listaNodos; }

	public void setListaNodos(NodoGrafo<T> valor, int coste) { this.listaNodos.agregar(valor, coste); }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }
	
	public void eliminarNodo(NodoGrafo<T> nodo) { this.listaNodos.eliminar(nodo); }
	
	public void mostrarListaNodosGrafoPonderado() { this.mostrarListaNodosGrafoPonderado(this.listaNodos.getCabeza()); }

	private void mostrarListaNodosGrafoPonderado(NodoLista<NodoGrafo<T>> aux) {
		if (!this.esNulo(aux)) {
			Consola.escribir("[" + aux.getDato().getElemento() + "," + aux.getCoste() + "]");
			this.mostrarListaNodosGrafoPonderado(aux.getSiguiente());
		}
	}

	public String[] obtenerLista() {
		String[] resultado = null;
		String r = this.obtenerLista(this.listaNodos.getCabeza(), "");
		resultado = r.split("-");
		return resultado;
	}

	private String obtenerLista(NodoLista<NodoGrafo<T>> aux, String resultado) {
		if (!this.esNulo(aux)) {
			resultado += aux.getDato().getElemento() + "," + aux.getCoste() + "-";
			resultado = this.obtenerLista(aux.getSiguiente(), resultado);
		}
		return resultado;
	}

	public boolean estaEnLista(T vertice) { return this.estaEnLista(this.listaNodos.getCabeza(), vertice); }

	private boolean estaEnLista(NodoLista<NodoGrafo<T>> aux, T vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return true;
			return this.estaEnLista(aux.getSiguiente(), vertice);
		}
		return false;
	}

	public boolean existenAdyacencias() {
    	if(!this.esNulo(this.listaNodos.getCabeza())) return true;
    	else return false;
    }

	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) { return nodo == null; }
}
