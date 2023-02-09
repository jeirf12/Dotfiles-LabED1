package Negocio;

public class ArbolBinario<T> {
	private Nodo<T> raiz;

	public ArbolBinario() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	public void setRaiz(Nodo<T> raiz) { this.raiz = raiz; }

	public boolean buscarElemento(T elemento) { return this.buscarElemento(this.raiz, elemento); }

	private boolean buscarElemento(Nodo<T> aux, T elemento) {
		if (aux == null) return false;
		if (aux.getElemento().equals(elemento)) return true;
		if (this.buscarElemento(aux.getIzq(), elemento)) return true;
		else if (this.buscarElemento(aux.getDer(), elemento)) return true;
		return false;
	}

	public void mostrarArbolSangrado() { this.mostrarArbolSangrado(this.raiz, 0); }

	private void mostrarArbolSangrado(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			this.mostrarArbolSangrado(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.mostrarArbolSangrado(arbol.getDer(), nivel + 1);
		}
	}
	
	public Nodo<T> retornarNodo(T elemento) { return this.retornarNodo(this.raiz, elemento); }

	private Nodo<T> retornarNodo(Nodo<T> aux, T elemento) {
		if (aux != null) {
			if (aux.getElemento() == elemento) return aux;
			if (this.buscarElemento(aux.getIzq(), elemento)) return this.retornarNodo(aux.getIzq(), elemento);
			else if (this.buscarElemento(aux.getDer(), elemento)) return this.retornarNodo(aux.getDer(),elemento);
		}
		return null;
	}
	
	public void insertar(T elemento) { this.raiz = new Nodo<T>(elemento); }

	public void insertar(T padre,T hijo,String tHijo){
		Nodo<T> temp;
		if (this.raiz != null) {
			temp = retornarNodo(padre);
			temp.insertarNodo(temp, hijo, tHijo);
		}
	}

	public void eliminar(T padre,T elemento) {
		if (padre != elemento) { 
			if (buscarElemento(elemento) && buscarElemento(padre)) {
				Nodo<T> aux = retornarNodo(padre);
				if (aux.getIzq().getElemento() == elemento) aux.setIzq(null);
				else if (aux.getDer().getElemento() == elemento) aux.setDer(null);
			}
		} else this.raiz = null;
	}
}
