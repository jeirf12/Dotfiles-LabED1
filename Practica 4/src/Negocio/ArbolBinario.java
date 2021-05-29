package Negocio;

public class ArbolBinario<T> {
	private Nodo<T> raiz;

	public ArbolBinario() {
		this.raiz = null;
	}

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}

	public boolean buscarElemento(T elemento) {
		return buscarElemento(raiz, elemento);
	}

	public boolean buscarElemento(Nodo<T> raiz, T elemento) {
		if (raiz == null) {
			return false;
		}
		if (raiz.getElemento().equals(elemento)) {
			return true;
		}
		if (buscarElemento(raiz.getIzq(), elemento)) {
			return true;
		} else if (buscarElemento(raiz.getDer(), elemento)) {
			return true;
		}
		return false;
	}

	public void mostrarArbolSangrado() {
		mostrarArbolSangrado(raiz, 0);
	}

	public void mostrarArbolSangrado(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			mostrarArbolSangrado(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			mostrarArbolSangrado(arbol.getDer(), nivel + 1);
		}
	}
	public Nodo<T> retornarNodo(T elemento){
		return retornarNodo(raiz, elemento);
	}
	public Nodo<T> retornarNodo(Nodo<T> raiz,T elemento){
		if (raiz != null) {
			if (raiz.getElemento() == elemento) {
				return raiz;
			}
			if (buscarElemento(raiz.getIzq(), elemento)) {
				return retornarNodo(raiz.getIzq(), elemento);
			} else if (buscarElemento(raiz.getDer(), elemento)) {
				return retornarNodo(raiz.getDer(),elemento);
			}
		}
		return null;
	}
	
	public void insertar(T elemento){
		raiz=new Nodo<T>(elemento);
	}
	public void insertar(T padre,T hijo,String tHijo){
		Nodo<T> temp;
		if (raiz!=null) {
			temp=retornarNodo(padre);
			temp=temp.insertarNodo(temp, hijo, tHijo);
		}
	}
	public void eliminar(T padre,T elemento) {
		if (padre!=elemento) {
			if (buscarElemento(elemento) && buscarElemento(padre)) {
				Nodo<T> aux=retornarNodo(padre);
				if (aux.getIzq().getElemento()==elemento) {
					aux.setIzq(null);
				}else if (aux.getDer().getElemento()==elemento) {
					aux.setDer(null);
				}
			}
		}else {
			raiz=null;
		}
	}
}
