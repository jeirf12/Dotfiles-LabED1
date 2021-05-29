package Negocio;


public class Lista<T> {
	private Nodo<T> cabeza;
	private int tamanio;

	public void agregar(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (esVacia()) {
			cabeza = nuevo;
		} else {
			agregar(cabeza, nuevo);
		}
		tamanio++;
	}
	private void agregar(Nodo<T> aux,Nodo<T> nuevo) {
		if (aux.getSiguiente()!=null) {
			agregar(aux.getSiguiente(), nuevo);
		}else {
			aux.setSiguiente(nuevo);
		}
	}
	//permite saber si esta vacia la lista
	public boolean esVacia() {
		return (cabeza==null);
	}
	private Nodo<T> obtenerNodoPosicion(int posicion){
		return obtenerNodoPosicion(cabeza, posicion, 0);
	}
	private Nodo<T> obtenerNodoPosicion(Nodo<T> nodo,int posicion,int contador){
		if (!esVacia()) {
			if (contador!=posicion) {
				return obtenerNodoPosicion(nodo.getSiguiente(), posicion, contador+1);
			}
		}
		return nodo;
	}
	
	public T obtenerElementoPosicion(int posicion) {
		return obtenerNodoPosicion(posicion).getDato();
	}
	//permite eliminar un elemento por su dato
	public boolean eliminarElemento(T dato) {
		boolean validaEliminacion=false;
		if (!esVacia()) {
			if (cabeza.getDato().equals(dato)) {
				cabeza=cabeza.getSiguiente();
				validaEliminacion=true;
			}
			else {
				validaEliminacion=eliminarElemento(cabeza, dato);
			}
			tamanio--;
		}
		return validaEliminacion;
	}
	private boolean eliminarElemento(Nodo<T> aux,T dato) {
		if (aux.getSiguiente()!=null) {
			if (!aux.getSiguiente().getDato().equals(dato)) {
				eliminarElemento(aux.getSiguiente(), dato);
			}else {
				aux.setSiguiente(aux.getSiguiente().getSiguiente());
				return true;
			}
		}
		return false;
		
	}
	//permite eliminar un elemento por su posicion
	public String eliminarElementoPosicion(int posicion) {
		String resultado="El dato no se encuentra en la posicion"+posicion+" porque la lista esta vacia";
		T dato;
		if (!esVacia()) {
			dato = this.obtenerElementoPosicion(posicion);
			if (this.eliminarElemento(dato)) {
				resultado = "El dato eliminado es: " + dato + " en la posicion " + posicion+" fue eliminado correctamente!";
			}
		}
		return resultado;
	}
	public void editarPorPosicion(int posicion, T valor) {
		if (posicion >= 0 && posicion < tamanio) {
			Nodo<T> nodo=this.obtenerNodoPosicion(posicion);
			nodo.setDato(valor);
		}
	}
	public void borrarLista() {
		cabeza=null;
		tamanio=0;
	}
	public int getTamanio() {
		return tamanio;
	}
 }
