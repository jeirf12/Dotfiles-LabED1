package Negocio;


public class NodoGrafo<T> {
	private Lista<NodoGrafo<T>> listaNodos;
	private T elemento;

	public NodoGrafo(T elemento) {
		this.listaNodos = new Lista<NodoGrafo<T>>();
		this.elemento = elemento;
	}

	public Lista<NodoGrafo<T>> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(NodoGrafo<T> valor,int coste) {
		this.listaNodos.agregar(valor,coste);
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	public void eliminarNodo(NodoGrafo<T> nodo) {
		this.listaNodos.eliminar(nodo);
	}
	
	public void mostrarListaNodosGrafoPonderado() {
		mostrarListaNodosGrafoPonderado(this.listaNodos.getCabeza());
	}
	private void mostrarListaNodosGrafoPonderado(NodoLista<NodoGrafo<T>> aux) {
		if (!esNulo(aux)) {
			Consola.escribir("["+aux.getDato().getElemento()+","+aux.getCoste()+"]");
			mostrarListaNodosGrafoPonderado(aux.getSiguiente());
		}
	}
	public String[] obtenerLista() {
		String[]resultado=null;
		String r="";
		r=obtenerLista(this.listaNodos.getCabeza(), r);
		resultado=r.split("-");
		return resultado;
	}
	private String obtenerLista(NodoLista<NodoGrafo<T>> aux,String resultado) {
		if (!esNulo(aux)) {
			resultado+=aux.getDato().getElemento()+","+aux.getCoste()+"-";
			resultado=obtenerLista(aux.getSiguiente(), resultado);
		}
		return resultado;
	}

	public boolean estaEnLista(T vertice){
        return estaEnLista(this.listaNodos.getCabeza(), vertice);
    }
	private boolean estaEnLista(NodoLista<NodoGrafo<T>> aux,T vertice) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) {
				return true;
			}
			return estaEnLista(aux.getSiguiente(),vertice);
		}
		return false;
	}
	public boolean existenAdyacencias() {
    	if(!esNulo(this.listaNodos.getCabeza())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
	
}
