package Negocio;

public class Nodo<T> {
	private T elemento;
	private Nodo<T> izq, der;

	public Nodo(T elemento) {
		this.elemento = elemento;
		this.izq = null;
		this.der = null;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public Nodo<T> getIzq() {
		return izq;
	}

	public void setIzq(Nodo<T> izq) {
		this.izq = izq;
	}

	public Nodo<T> getDer() {
		return der;
	}

	public void setDer(Nodo<T> der) {
		this.der = der;
	}

	@SuppressWarnings("unchecked")
	public Nodo<T> insertarNodo (Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) {
			return new Nodo<T>((T) elemento);
		} else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.izq = insertarNodo(arbol.getIzq(), elemento);
		} else if (elemento.compareTo(arbol.getElemento()) > 0) {
			arbol.der = insertarNodo(arbol.getDer(), elemento);
		}
		return arbol;
	}
}
