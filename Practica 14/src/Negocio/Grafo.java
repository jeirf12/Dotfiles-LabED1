package Negocio;

import java.util.ArrayList;

public class Grafo<T> {
	private Lista<NodoGrafo<T>> listaVertices;

	public Grafo() { listaVertices = new Lista<NodoGrafo<T>>(); }

	public void agregarVertice(T vertice) { if (!this.verificarVertice(vertice)) this.listaVertices.agregar(new NodoGrafo<T>(vertice)); }

	public Lista<NodoGrafo<T>> getListaVertices() { return this.listaVertices; }

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) { this.listaVertices = listaVertices; }

	private ArrayList<NodoGrafo<T>> verticesGrafo() {
		ArrayList<NodoGrafo<T>> aristas = new ArrayList<NodoGrafo<T>>();
		System.out.println("Grafo No Dirigido G=(V,E)");
		System.out.println("------------------------");
		NodoLista<NodoGrafo<T>> aux = listaVertices.getCabeza();
		System.out.print("({ ");
		this.verticesGrafo(aux);
		System.out.print("},");
		aristas = this.imprimirAdyacencias();
		System.out.println("------------------------");
		return aristas;
	}

	private void verticesGrafo(NodoLista<NodoGrafo<T>> aux) {
		if (!this.esNulo(aux)) {
			System.out.print(aux.getDato().getElemento() + " ");
			this.verticesGrafo(aux.getSiguiente());
		}
	}
	
	public boolean verificarVertice(T vertice) { return this.verificarVertice(this.listaVertices.getCabeza(), vertice); }

	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux, T vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return true;
			return this.verificarVertice(aux.getSiguiente(), vertice);
		}
		return false;
	}
	
	public boolean verificarArista(T vInicial, T vFinal) {
		NodoGrafo<T> verInicial = this.retornarVertice(vInicial);
		NodoGrafo<T> verFinal = this.retornarVertice(vFinal);
		if (verInicial != null && verFinal != null) {
			NodoLista<NodoGrafo<T>> aux = verInicial.getListaNodos().getCabeza();
			return this.verificarArista(aux, verFinal);
		}
		return false;
	}

	private boolean verificarArista(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vFinal) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vFinal.getElemento())) return true;
			return this.verificarArista(aux.getSiguiente(), vFinal);
		}
		return false;
	}
	
	public NodoGrafo<T> retornarVertice(T vertice) { return this.retornarVertice(this.listaVertices.getCabeza(), vertice); }

	private NodoGrafo<T> retornarVertice(NodoLista<NodoGrafo<T>> aux, T vertice){
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return aux.getDato();
			return this.retornarVertice(aux.getSiguiente(), vertice);
		}
		return null;
	}
	
	public void agregarArista(T vInicial, T vFinal){
		NodoGrafo<T> nodoI = null, nodoF = null;
		if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
			if (!this.verificarArista(vInicial, vFinal)) {
				nodoI = this.retornarVertice(vInicial);
				nodoF = this.retornarVertice(vFinal);
				if (nodoI != null && nodoF != null) {
					nodoI.setListaNodos(nodoF);
					nodoF.setListaNodos(nodoI);
				}
			} else System.out.println("La arista ya existe");
		}else System.out.println("Alguno de los vertices no existe");
	}
	
	public ArrayList<NodoGrafo<T>> imprimirAdyacencias() {
		ArrayList<NodoGrafo<T>> aristas = new ArrayList<NodoGrafo<T>>();
		NodoLista<NodoGrafo<T>> aux = this.listaVertices.getCabeza();
		System.out.print("{");
		aristas = this.imprimirAdyacencias(aux, aristas);
		System.out.print("})");
		System.out.println();
		return aristas;
	}

	private ArrayList<NodoGrafo<T>> imprimirAdyacencias(NodoLista<NodoGrafo<T>> aux, ArrayList<NodoGrafo<T>> aristas){
		if (!this.esNulo(aux)) {
			aristas = this.mostrarAdyacencias(aux.getDato().getElemento(), aristas);
			aristas = this.imprimirAdyacencias(aux.getSiguiente(), aristas);
		}
		return aristas;
	}
	
	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(T vertice, ArrayList<NodoGrafo<T>> aristas){
		NodoGrafo<T> nodoVertice = this.retornarVertice(vertice);
		if (nodoVertice != null) {
			NodoLista<NodoGrafo<T>> aux = nodoVertice.getListaNodos().getCabeza();
			aristas = this.mostrarAdyacencias(aux, nodoVertice,aristas);
		}
		return aristas;
	}

	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vertice, ArrayList<NodoGrafo<T>> nodos) {
		if (!this.esNulo(aux) && !aux.getDato().getListaNodos().esVacia()) {
			System.out.print("("+vertice.getElemento()+","+aux.getDato().getElemento()+")");
			vertice.eliminarArista(aux.getDato());
			aux.getDato().eliminarArista(vertice);
			nodos.add(vertice);
			nodos.add(aux.getDato());
			nodos = this.mostrarAdyacencias(aux.getSiguiente(),vertice,nodos);
		}
		return nodos;
	}
	
	public void imprimirTodoGrafo() {
		ArrayList<NodoGrafo<T>> aristas = new ArrayList<NodoGrafo<T>>();
		aristas = this.verticesGrafo();
		//Este metodo sirve para devolver las adyacencias que se eliminan, cuando se esta mostrando con el metodo mostrarAdyacencias Sobrecarga2
		this.regresarAristas(aristas, 0);
	}

	private void regresarAristas(ArrayList<NodoGrafo<T>> aristas, int contador) {
		if(contador<aristas.size()) {
			this.agregarArista(aristas.get(contador).getElemento(), aristas.get(contador + 1).getElemento());
			this.regresarAristas(aristas, contador + 2);
		}
	}

	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) { return nodo == null; }
}
