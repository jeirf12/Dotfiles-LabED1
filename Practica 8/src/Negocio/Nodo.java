package Negocio;

public class Nodo <T>{
	private T elemento;
	private Nodo<T> izq, der;

	public Nodo(T elemento) { this.elemento = elemento; this.izq = null; this.der = null; }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }

	public Nodo<T> getIzq() { return this.izq; }

	public void setIzq(Nodo<T> izq) { this.izq = izq; }

	public Nodo<T> getDer() { return this.der; }

	public void setDer(Nodo<T> der) { this.der = der; }

	@SuppressWarnings("unchecked")
	public Nodo<T> insertarNodo(Nodo<T> n, Comparable<T> elemento) {
		if (n == null) return new Nodo<T>((T) elemento);
		else if (elemento.compareTo(n.getElemento()) < 0) n.izq = insertarNodo(n.getIzq(), elemento);
		else if (elemento.compareTo(n.getElemento()) > 0) n.der = insertarNodo(n.getDer(), elemento);
		return n;
	}
}
