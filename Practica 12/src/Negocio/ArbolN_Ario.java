package Negocio;

import java.util.ArrayList;

public class ArbolN_Ario <T>{
	private NodoArbol<T> raiz;
	
	public ArbolN_Ario() {
		this.raiz=null;
	}
	public NodoArbol<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoArbol<T> raiz) {
		this.raiz = raiz;
	}
	
	public void insertarRaiz(T dato) {
		this.raiz=new NodoArbol<T>(dato);
	}
	
	public void agregar(T padre,T hijo) {
		agregar(raiz, padre, hijo);
	}
	private void agregar(NodoArbol<T> nodo,T padre,T hijo) {
		NodoArbol<T> nuevo=new NodoArbol<T>(hijo);
		if (nodo.getElemento().equals(padre)) {
			nodo.setHijos(nuevo);
		}else {
			NodoLista<NodoArbol<T>> aux=nodo.getHijos().getCabeza();
			agregar(aux, padre, nuevo);
		}
	}
	private void agregar(NodoLista<NodoArbol<T>> aux,T padre,NodoArbol<T> nuevo) {
		if (!esNulo(aux)) {
			if (aux.getDato().equals(padre)) {
				aux.getDato().setHijos(nuevo);
			}else {
				agregar(aux.getDato(), padre, nuevo.getElemento());
			}
			agregar(aux.getSiguiente(), padre, nuevo);
		}
	}
	
	public void imprimirNivelesArbol() {
		System.out.println("Recorrido Por Niveles");
		imprimirNivelesArbol(raiz, 0);
	}
	private void imprimirNivelesArbol(NodoArbol<T> arbol, int nivel) {
		if (arbol.getHijos().getTamanio() > 0) {
			System.out.println(" " + nivel + " " + arbol.getElemento());
			NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
			imprimirNivelesArbol(aux, nivel);
		}else {
			System.out.println(" " + nivel + " " + arbol.getElemento());
		}
	}
	private void imprimirNivelesArbol(NodoLista<NodoArbol<T>> aux,int nivel) {
		if (!esNulo(aux)) {
			imprimirNivelesArbol(aux.getDato(), nivel+1);
			imprimirNivelesArbol(aux.getSiguiente(), nivel);
		}
	}
	
	public boolean buscarElemento(T elemento) {
		return buscarElemento(raiz,elemento);
	}	
	private boolean buscarElemento(NodoArbol<T> arbol, T elemento) {
		if (arbol.getElemento().equals(elemento)) {
			return true;
		} else {
			NodoLista<NodoArbol<T>> aux = arbol.getHijos().getCabeza();
			return buscarElemento(aux, elemento);
		}
	}
	private boolean buscarElemento(NodoLista<NodoArbol<T>> aux,T elemento) {
		if (!esNulo(aux)) {
			if (buscarElemento(aux.getDato(),elemento)) {
				return true;
			}
			return buscarElemento(aux.getSiguiente(), elemento);
		}
		return false;
	}
	
	public void alturaArbol() {
		System.out.println("La altura del árbol es: "+(alturaArbol(raiz, 0, 0)+1));
	}
	private int alturaArbol(NodoArbol<T> arbol,int nivel ,int altura) {
		if (nivel>altura) {
			altura=nivel;
		}
		NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
		return alturaArbol(aux, nivel, altura);
	}
	private int alturaArbol(NodoLista<NodoArbol<T>> aux,int nivel,int altura) {
		if (!esNulo(aux)) {
			altura=alturaArbol(aux.getDato(), nivel+1, altura);
			return alturaArbol(aux.getSiguiente(), nivel, altura);
		}
		return altura;
	}
	
	public void contarHijos() {
		ArrayList<NodoArbol<T>> nodos=new ArrayList<NodoArbol<T>>();
		nodos=contarHijos(raiz,nodos,0);
		System.out.println("El/Los Nodo de mayor numero de hijos es/son: ");
		for (int i = 0; i < nodos.size(); i++) {
			System.out.println("Nodo: "+nodos.get(i).getElemento()+" N° Hijos: "+nodos.get(i).getHijos().getTamanio());
		}
	}
	private ArrayList<NodoArbol<T>> contarHijos(NodoArbol<T> arbol,ArrayList<NodoArbol<T>> nodos,int mayor) {
		if (arbol.getHijos().getTamanio()>=mayor) {
			mayor=arbol.getHijos().getTamanio();
			nodos.add(arbol);
		}
		NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
		return contarHijos(aux, nodos, mayor);
	}
	private ArrayList<NodoArbol<T>> contarHijos(NodoLista<NodoArbol<T>> aux,ArrayList<NodoArbol<T>> nodos,int mayor){
		if (!esNulo(aux)) {
			nodos=contarHijos(aux.getDato(), nodos, mayor);
			return contarHijos(aux.getSiguiente(),nodos,mayor);
		}
		return nodos;
	}
	
	public void mostrarHijosNodo(T nodo) {
		NodoArbol<T> varNodo=retornarNodo(nodo);
		if (varNodo!=null) {
			if (varNodo.getHijos().getTamanio()>0) {
				NodoLista<NodoArbol<T>> aux=varNodo.getHijos().getCabeza();
				System.out.println("Los hijos del nodo "+nodo+" son: ");
				mostrarHijosNodo(aux);
				System.out.println();
			}else {
				System.out.println("El dato no tiene hijos, porque es una hoja");
			}
		}else {
			System.out.println("El dato ingresado no tiene hijos, porque no existe el nodo");
		}
	}
	private void mostrarHijosNodo(NodoLista<NodoArbol<T>> aux) {
		if (!esNulo(aux)) {
			System.out.print(" "+aux.getDato().getElemento());
			mostrarHijosNodo(aux.getSiguiente());
		}
	}
	
	public void mostrarNodosNivel(int n) {
		int altura=alturaArbol(raiz, 0, 0);
		System.out.println("Los nodos del nivel "+n+" son: ");
		mostrarNodosNivel(raiz, n, 0,altura);
		System.out.println();
	}
	private void mostrarNodosNivel(NodoArbol<T> arbol, int n, int nivel,int altura) {
		if (n<=altura) {
			if (n != nivel) {
				NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
				mostrarNodosNivel(aux, n, nivel, altura);
			}else if (n==nivel) {
				System.out.print(" "+arbol.getElemento());
			}
		}else {
			System.out.println("El nivel ingresado no existe en el árbol");
		}
	}
	private void mostrarNodosNivel(NodoLista<NodoArbol<T>> aux,int n,int nivel,int altura) {
		if (!esNulo(aux)) {
			mostrarNodosNivel(aux.getDato(), n, nivel, altura);
			mostrarNodosNivel(aux.getSiguiente(), n, nivel, altura);
		}
	}
	
	private NodoArbol<T> retornarNodo(T dato) {
		if (buscarElemento(dato)) {
			return retornarNodo(raiz, dato);
		}else {
			return null;
		}
		
	}
	private NodoArbol<T> retornarNodo(NodoArbol<T> arbol,T dato) {
		NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
		return retornarNodo(aux, arbol, dato);
	}
	private NodoArbol<T> retornarNodo(NodoLista<NodoArbol<T>> aux,NodoArbol<T> arbol,T dato){
		if (!esNulo(aux)) {
			if (arbol.getElemento().equals(dato)) {
				return arbol;
			}
			arbol=retornarNodo(aux.getDato(),dato);
			return retornarNodo(aux.getSiguiente(), arbol, dato);
		}
		return arbol;
	}
	public void padreNodo(T dato) {
		if (buscarElemento(raiz,dato)) {
			NodoArbol<T> padre=retornarPadre(raiz, null, dato);
			if (padre!=null) {
				System.out.println("El padre es "+padre.getElemento());
			}else {
				System.out.println("No tiene padre porque es la raíz");
			}
		}else {
			System.out.println("El nodo no esta en el árbol");
		}
	}
	private NodoArbol<T> retornarPadre(NodoArbol<T> arbol,NodoArbol<T> padre, T dato){
		if (!validaHijos(arbol, dato)) {
			NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
			return retornarPadre(aux, padre, dato);
		}
		return arbol;
	}
	private NodoArbol<T> retornarPadre(NodoLista<NodoArbol<T>> aux,NodoArbol<T> padre,T dato){
		if (!esNulo(aux)) {
			padre=retornarPadre(aux.getDato(), padre, dato);
			return retornarPadre(aux.getSiguiente(), padre, dato);
		}
		return padre;
	}
	private boolean validaHijos(NodoArbol<T> arbol, T dato) {
		NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
		return validaHijos(aux, dato);
	}
	private boolean validaHijos(NodoLista<NodoArbol<T>> aux,T dato) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(dato)) {
				return true;
			}
			return validaHijos(aux.getSiguiente(), dato);
		}
		return false;
	}
	public void nivelNodo(T dato) {
		int nivel=0;
		if (buscarElemento(dato)) {
			nivel=nivelNodo(raiz, 0, 0, dato);
			if (nivel>0) {
				System.out.println("El dato "+dato+" nivel "+nivel+" del árbol");
			}else {
				System.out.println("El dato "+dato+" nivel "+nivel+" del árbol es la raíz");
			}
		}else {
			System.out.println("El nodo no esta en el árbol");
		}
	}
	private int nivelNodo(NodoArbol<T> arbol,int nivel,int nivelDato,T dato) {
		if (!arbol.getElemento().equals(dato)) {
			NodoLista<NodoArbol<T>> aux=arbol.getHijos().getCabeza();
			return nivelNodo(aux, nivel, nivelDato, dato);
		}
		return nivel;
	}
	private int nivelNodo(NodoLista<NodoArbol<T>> aux,int nivel,int nivelDato,T dato) {
		if (!esNulo(aux)) {
			nivelDato=nivelNodo(aux.getDato(), nivel+1, nivelDato, dato);
			return nivelNodo(aux.getSiguiente(), nivel, nivelDato, dato);
		}
		return nivelDato;
	}
	public void mostrarHermanos(T dato) {
		 if (buscarElemento(raiz, dato)) {
			 NodoArbol<T> padre=retornarPadre(raiz,null, dato);
			 if (padre!=null) {
				 if (padre.getHijos().getTamanio()>1) {
					 System.out.println("Los hermanos del dato "+dato+" son: ");
					 NodoLista<NodoArbol<T>> aux=padre.getHijos().getCabeza();
					 mostrarHermanos(aux, dato);
					 System.out.println();
				 }else {
					 System.out.println("el dato "+dato+" no tiene hermanos");
				 }
			 }else {
				 System.out.println("El dato "+dato+" no tiene hermanos es la raíz");
			 }
		 }else {
			 System.out.println("El nodo no esta en el árbol");
		 }
	 }
	private void mostrarHermanos(NodoLista<NodoArbol<T>> aux,T dato) {
		if (!esNulo(aux)) {
			NodoArbol<T> hermano=aux.getDato();
			if (!hermano.getElemento().equals(dato)) {
				System.out.print(" "+hermano.getElemento());
			}
			mostrarHermanos(aux.getSiguiente(), dato);
		}
	}
	private boolean esNulo(NodoLista<NodoArbol<T>> nodo) {
		return nodo==null;
	}
}
