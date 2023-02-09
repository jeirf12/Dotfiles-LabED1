package Logica;

public class Lista<T> {
	private Nodo<T> cabeza;
	private int tamanio;
	
	public Lista() { this.cabeza = null; this.tamanio = 0; }
	
	public void setCabeza(Nodo<T> cabeza) { this.cabeza = cabeza; }

	public Nodo<T> getCabeza(){ return this.cabeza; }

	public void setTamanio(int tamanio) { this.tamanio = tamanio; }

	public int getTamanio() { return this.tamanio; }

	public void agregar(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (!esVacia()) agregar(cabeza).setSiguiente(nuevo);
		else cabeza = nuevo;
		tamanio++;
	}

	private Nodo<T> agregar(Nodo<T> aux){
		if (aux.getSiguiente() != null) return agregar(aux.getSiguiente());
		return aux;
	}

	public boolean esVacia() { return this.cabeza == null; }

	private void mostrar(Nodo<T> aux){
		if (aux != null) {
			Consola.escribirSaltarLinea(aux.getDato().toString());
			mostrar(aux.getSiguiente());
		}
	}

	public void mostrar(){
		Consola.escribirSaltarLinea("C O N T E N I D O  D E  L A  L I S T A");
		mostrar(cabeza);
	}
}
