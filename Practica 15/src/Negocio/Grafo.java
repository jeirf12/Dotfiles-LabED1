package Negocio;

import java.util.ArrayList;

public class Grafo <T>{
	private Lista<NodoGrafo<T>> listaVertices;

	public Grafo() { this.listaVertices = new Lista<NodoGrafo<T>>(); }

	public Lista<NodoGrafo<T>> getListaVertices() { return this.listaVertices; }

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) { this.listaVertices = listaVertices; }
	
	public boolean verificarVertice(T vertice) { return this.verificarVertice(this.listaVertices.getCabeza(), vertice); }

	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux, T vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return true;
			return this.verificarVertice(aux.getSiguiente(), vertice);
		}
		return false;
	}
	
	public void agregarVertice(T vertice) { if (!this.verificarVertice(vertice)) this.getListaVertices().agregar(new NodoGrafo<T>(vertice)); }
	
	public NodoGrafo<T> retornarVertice(T vertice) { return this.retornarVertice(this.listaVertices.getCabeza(), vertice); }

	private NodoGrafo<T> retornarVertice(NodoLista<NodoGrafo<T>> aux, T vertice){
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return aux.getDato();
			return this.retornarVertice(aux.getSiguiente(), vertice);
		}
		return null;
	}
	
	public boolean verificarArista(T vInicial, T vFinal){
		NodoGrafo<T> verInicial = this.retornarVertice(vInicial);
		NodoGrafo<T> verFinal = this.retornarVertice(vFinal);
		if (verInicial != null && verFinal != null) return this.verificarArista(verInicial.getListaNodos().getCabeza(), verFinal);
		return false;
	}

	private boolean verificarArista(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vFinal) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vFinal.getElemento())) return true;
			return this.verificarArista(aux.getSiguiente(), vFinal);
		}
		return false;
	}
	
	public void agregarArista(T vInicial, T vFinal) {
		NodoGrafo<T> nodoI = null, nodoF = null;
		if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
			if (!this.verificarArista(vInicial, vFinal)) {
				if (!vInicial.equals(vFinal)) {
					nodoI = this.retornarVertice(vInicial);
					nodoF = this.retornarVertice(vFinal);
					if (nodoI != null && nodoF != null) {
						nodoI.setListaNodos(nodoF);
						nodoF.setListaNodos(nodoI);
					}
				} else System.out.println("No se permiten bucles o lazos");
			} else System.out.println("La arista ya existe");
		} else System.out.println("Alguno de los vertices no existe");
	}
	
	private ArrayList<NodoGrafo<T>> verticesGrafo() {
		ArrayList<NodoGrafo<T>> aristas = new ArrayList<NodoGrafo<T>>();
		System.out.println("Grafo No Dirigido G=(V,E)");
		System.out.println("------------------------");
		NodoLista<NodoGrafo<T>> aux = this.listaVertices.getCabeza();
		System.out.print("({ ");
		this.verticesGrafo(aux);
		System.out.print("},");
		aristas = this.imprimirAdyacencias();
		System.out.println("------------------------");
		return aristas;
	}

	private void verticesGrafo(NodoLista<NodoGrafo<T>> aux){
		if (!this.esNulo(aux)) {
			System.out.print(aux.getDato().getElemento() + " ");
			this.verticesGrafo(aux.getSiguiente());
		}
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

	private ArrayList<NodoGrafo<T>> imprimirAdyacencias(NodoLista<NodoGrafo<T>> aux, ArrayList<NodoGrafo<T>> aristas) {
		if (!this.esNulo(aux)) {
			aristas = this.mostrarAdyacencias(aux.getDato().getElemento(), aristas);
			aristas = this.imprimirAdyacencias(aux.getSiguiente(), aristas);
		}
		return aristas;
	}
	
	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(T vertice, ArrayList<NodoGrafo<T>> aristas) {
		NodoGrafo<T> nodoVertice = this.retornarVertice(vertice);
		if (nodoVertice != null) aristas = this.mostrarAdyacencias(nodoVertice.getListaNodos().getCabeza(), nodoVertice,aristas);
		return aristas;
	}

	private ArrayList<NodoGrafo<T>> mostrarAdyacencias(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vertice, ArrayList<NodoGrafo<T>> nodos) {
		if (!this.esNulo(aux) && !aux.getDato().getListaNodos().esVacia()) {
			System.out.print("(" + vertice.getElemento() + "," + aux.getDato().getElemento() + ")");
			vertice.eliminarArista(aux.getDato());
			aux.getDato().eliminarArista(vertice);
			nodos.add(vertice);
			nodos.add(aux.getDato());
			nodos = this.mostrarAdyacencias(aux.getSiguiente(), vertice, nodos);
		}
		return nodos;
	}
	
	private void matrizAdyacencias() {
		int[][] matrizAdy = new int[this.listaVertices.getTamanio()][this.listaVertices.getTamanio()];
		NodoLista<NodoGrafo<T>> aux1 = this.listaVertices.getCabeza();
		Lista<NodoGrafo<T>> listTemp = this.listaVertices;
		NodoLista<NodoGrafo<T>> aux2 = new NodoLista<NodoGrafo<T>>();
		for (int i = 0; i < matrizAdy.length; i++) {
			aux2 = listTemp.getCabeza();
			for (int j = 0; j < matrizAdy.length; j++) {
				if (verificarArista(aux1.getDato().getElemento(), aux2.getDato().getElemento())) matrizAdy[i][j] = 1;
				else matrizAdy[i][j] = 0;
				aux2 = aux2.getSiguiente();
			}
			aux1 = aux1.getSiguiente();
		}
		this.imprimirMatriz(matrizAdy);
	}

	private void imprimirMatriz(int[][] matriz) {
		NodoLista<NodoGrafo<T>> aux = this.listaVertices.getCabeza();
		System.out.println("La matriz de adyacencias del grafo es: ");
		System.out.println("------------------------");
		System.out.print(" ");
		this.imprimirVerticesHorizontalmente(this.listaVertices.getCabeza());
		System.out.println();
		for (int i = 0; i < matriz.length; i++) {
			System.out.print(aux.getDato().getElemento() + "  ");
			for (int j = 0; j < matriz.length; j++) System.out.print(matriz[i][j] + "  ");
			System.out.println();
			aux = aux.getSiguiente();
		}
		System.out.println("------------------------");
	}
	
	private void imprimirVerticesHorizontalmente(NodoLista<NodoGrafo<T>> aux) {
		if (!this.esNulo(aux)) {
			System.out.print("  " + aux.getDato().getElemento());
			this.imprimirVerticesHorizontalmente(aux.getSiguiente());
		}
	}
	
	private void regresarAristas(ArrayList<NodoGrafo<T>> aristas, int contador) {
		if(contador < aristas.size()) {
			this.agregarArista(aristas.get(contador).getElemento(), aristas.get(contador + 1).getElemento());
			this.regresarAristas(aristas, contador+2);
		}
		
	}

	public void imprimirTodoGrafo() {
		ArrayList<NodoGrafo<T>> aristas = new ArrayList<NodoGrafo<T>>();
		aristas = this.verticesGrafo();
		this.regresarAristas(aristas, 0);
		this.matrizAdyacencias();
	}

	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) { return nodo == null; }
}
