package Negocio;

import java.util.ArrayList;

public class Grafo<T> {
	private Lista<NodoGrafo<T>> listaVertices;

	public Grafo() {
		listaVertices = new Lista<NodoGrafo<T>>();
	}

	public void agregarVertice(T vertice) {
		if (!verificarVertice(vertice)) {
			NodoGrafo<T> g = new NodoGrafo<T>(vertice);
			this.getListaVertices().agregar(g);
		}
	}

	public Lista<NodoGrafo<T>> getListaVertices() {
		return listaVertices;
	}

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) {
		this.listaVertices = listaVertices;
	}

	private ArrayList<NodoGrafo<T>> verticesGrafo(){
		ArrayList<NodoGrafo<T>> aristas=new ArrayList<NodoGrafo<T>>();
		System.out.println("Grafo No Dirigido G=(V,E)");
		System.out.println("------------------------");
		NodoLista<NodoGrafo<T>> aux=listaVertices.getCabeza();
		System.out.print("({ ");
		verticesGrafo(aux);
		System.out.print("},");
		aristas=imprimirAdyacencias();
		System.out.println("------------------------");
		return aristas;
	}
	private void verticesGrafo(NodoLista<NodoGrafo<T>> aux){
		if (!esNulo(aux)) {
			System.out.print(aux.getDato().getElemento()+" ");
			verticesGrafo(aux.getSiguiente());
		}
	}
	
	public boolean verificarVertice(T vertice){
		return verificarVertice(listaVertices.getCabeza(), vertice);
	}
	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux,T vertice) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) {
				return true;
			}
			return verificarVertice(aux.getSiguiente(), vertice);
		}
		return false;
	}
	
	public boolean verificarArista(T vInicial, T vFinal){
		NodoGrafo<T> verInicial = retornarVertice(vInicial);
		NodoGrafo<T> verFinal = retornarVertice(vFinal);
		if (verInicial!=null && verFinal!=null) {
			NodoLista<NodoGrafo<T>> aux = verInicial.getListaNodos().getCabeza();
			return verificarArista(aux, verFinal);
		}
		return false;
	}
	private boolean verificarArista(NodoLista<NodoGrafo<T>> aux,NodoGrafo<T> vFinal) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vFinal.getElemento())) {
				return true;
			}
			return verificarArista(aux.getSiguiente(), vFinal);
		}
		return false;
	}
	
	public NodoGrafo<T> retornarVertice(T vertice){
		return retornarVertice(listaVertices.getCabeza(), vertice);
	}
	private NodoGrafo<T> retornarVertice(NodoLista<NodoGrafo<T>> aux,T vertice){
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) {
				return aux.getDato();
			}
			return retornarVertice(aux.getSiguiente(), vertice);
		}
		return null;
	}
	
	public void agregarArista(T vInicial,T vFinal){
		NodoGrafo<T> nodoI=null,nodoF=null;
		if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
			if (!verificarArista(vInicial, vFinal)) {
				nodoI=retornarVertice(vInicial);
				nodoF=retornarVertice(vFinal);
				if (nodoI!=null && nodoF!=null) {
					nodoI.setListaNodos(nodoF);
					nodoF.setListaNodos(nodoI);
				}
			}else{
				System.out.println("La arista ya existe");
			}
		}else {
			System.out.println("Alguno de los vertices no existe");
		}
	}
	
	public ArrayList<NodoGrafo<T>> imprimirAdyacencias() {
		ArrayList<NodoGrafo<T>> aristas=new ArrayList<NodoGrafo<T>>();
		NodoLista<NodoGrafo<T>> aux=listaVertices.getCabeza();
		System.out.print("{");
		aristas=imprimirAdyacencias(aux, aristas);
		System.out.print("})");
		System.out.println();
		return aristas;
	}
	private ArrayList<NodoGrafo<T>> imprimirAdyacencias(NodoLista<NodoGrafo<T>> aux,ArrayList<NodoGrafo<T>> aristas){
		if (!esNulo(aux)) {
			aristas=mostrarAdyacencias(aux.getDato().getElemento(), aristas);
			aristas=imprimirAdyacencias(aux.getSiguiente(), aristas);
		}
		return aristas;
	}
	
	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(T vertice,ArrayList<NodoGrafo<T>> aristas){
		NodoGrafo<T> nodoVertice=retornarVertice(vertice);
		if (nodoVertice!=null) {
			NodoLista<NodoGrafo<T>> aux=nodoVertice.getListaNodos().getCabeza();
			aristas=mostrarAdyacencias(aux, nodoVertice,aristas);
		}
		return aristas;
	}
	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(NodoLista<NodoGrafo<T>> aux,NodoGrafo<T> vertice,ArrayList<NodoGrafo<T>> nodos) {
		if (!esNulo(aux) && !aux.getDato().getListaNodos().esVacia()) {
			System.out.print("("+vertice.getElemento()+","+aux.getDato().getElemento()+")");
			vertice.eliminarArista(aux.getDato());
			aux.getDato().eliminarArista(vertice);
			nodos.add(vertice);
			nodos.add(aux.getDato());
			nodos=mostrarAdyacencias(aux.getSiguiente(),vertice,nodos);
		}
		return nodos;
	}
	
	public void imprimirTodoGrafo() {
		ArrayList<NodoGrafo<T>> aristas= new ArrayList<NodoGrafo<T>>();
		aristas=verticesGrafo();
		//Este metodo sirve para devolver las adyacencias que se eliminan, cuando se esta mostrando con el metodo mostrarAdyacencias Sobrecarga2
		regresarAristas(aristas,0);
	}
	private void regresarAristas(ArrayList<NodoGrafo<T>> aristas,int contador) {
		if(contador<aristas.size()) {
			agregarArista(aristas.get(contador).getElemento(), aristas.get(contador+1).getElemento());
			regresarAristas(aristas, contador+2);
		}
	}
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
}
