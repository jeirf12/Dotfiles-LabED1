package Negocio;

public class Nodo<T> {
	private T elemento;
	private Nodo<T> izq, der;

	public Nodo(T elemento) { this.elemento = elemento; this.izq = null; this.der = null; }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }

	public Nodo<T> getIzq() { return this.izq; }

	public void setIzq(Nodo<T> izq) { this.izq = izq; }

	public Nodo<T> getDer() { return this.der; }

	public void setDer(Nodo<T> der) { this.der = der; } 

	public Nodo<T> insertarNodo(Nodo<T> aux, T elemento, String hijo){
		if (aux != null) {
			if (hijo.equals("I")) aux.izq = new Nodo<T>(elemento);
			else if(hijo.equals("D")) aux.der = new Nodo<T>(elemento);
		}
		return aux;
	}
}
