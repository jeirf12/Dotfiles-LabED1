package Negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo<T> {
	// Atributos
	private Lista<NodoGrafo<T>> listaVertices;

	// Constructores
	public Grafo() { this.listaVertices = new Lista<NodoGrafo<T>>(); }

	// M�todos getters and setters y los solicitados en la pr�ctica
	public void agregarVertice(T vertice) { if (!this.verificarVertice(vertice)) this.getListaVertices().agregar(new NodoGrafo<T>(vertice)); }

	public Lista<NodoGrafo<T>> getListaVertices() { return this.listaVertices; }

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) { this.listaVertices = listaVertices; }

	public void verticesGrafoPonderado(){
		Consola.escribirSaltarLinea("------------------------");
		Consola.escribirSaltarLinea("Vertices grafo");
		Consola.escribirSaltarLinea("------------------------");
		this.verticesGrafoPonderado(this.listaVertices.getCabeza());
		Consola.escribirSaltarLinea("------------------------");
	}

	private void verticesGrafoPonderado(NodoLista<NodoGrafo<T>> aux) {
		if (!this.esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento()+" ->");
			aux.getDato().mostrarListaNodosGrafoPonderado();
			Consola.escribirSaltarLinea("");
			this.verticesGrafoPonderado(aux.getSiguiente());
		}
	}
	
	/**
     	* Logitud de camino con m�s proceso a la hora de imprimir (opcional utilizarlo)
     	*/
	public void longitudCamino() {
		int coste = 0;
		Consola.escribir("T = ");
		coste = this.longitudCamino(listaVertices.getCabeza(), coste);
		Consola.escribirSaltarLinea(" Longitud de camino = " + coste);
	}

	private int longitudCamino(NodoLista<NodoGrafo<T>> aux, int coste) {
		if (!this.esNulo(aux)) {
			coste = this.longitudCamino(aux, aux.getDato().getListaNodos().getCabeza(), coste);
			coste = this.longitudCamino(aux.getSiguiente(), coste);
		}
		return coste;
	}

	private int longitudCamino(NodoLista<NodoGrafo<T>> aux, NodoLista<NodoGrafo<T>> aux2,int coste) {
		if (!this.esNulo(aux2)) {
			Consola.escribir("[" + aux.getDato().getElemento() + "-" + aux2.getDato().getElemento() + "," + aux2.getCoste() + "],");
			aux2.getDato().eliminarNodo(aux.getDato());
			coste += aux2.getCoste();
			return this.longitudCamino(aux, aux2.getSiguiente(), coste);
		}
		return coste;
	}
	
	public boolean verificarVertice(T vertice) { return this.verificarVertice(this.listaVertices.getCabeza(), vertice); }

	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux, T vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return true;
			return this.verificarVertice(aux.getSiguiente(),vertice);
		}
		return false;
	}
	
	public boolean verificarArista(T vInicial, T vFinal) {
		if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
			NodoGrafo<T> verInicial = this.retornarVertice(vInicial);
			NodoGrafo<T> verFinal = this.retornarVertice(vFinal);
			if (verInicial != null && verFinal != null) return this.verificarArista(verInicial.getListaNodos().getCabeza(), verFinal);
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

	private NodoGrafo<T> retornarVertice(NodoLista<NodoGrafo<T>> aux,T vertice){
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return aux.getDato();
			return this.retornarVertice(aux.getSiguiente(), vertice);
		}
		return null;
	}
	
	public void agregarArista(T vInicial, T vFinal, int coste) {
		NodoGrafo<T> nodoI = null, nodoF = null;
		if (coste > 0) {
			if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
				if (!vInicial.equals(vFinal)) {
					if (!this.verificarArista(vInicial, vFinal)) {
						nodoI = this.retornarVertice(vInicial);
						nodoF = this.retornarVertice(vFinal);
						nodoI.setListaNodos(nodoF, coste);
						nodoF.setListaNodos(nodoI, coste);
					} else Consola.escribirSaltarLinea("La arista ya existe");
				} else Consola.escribirSaltarLinea("No se admite bucles");
			}else Consola.escribirSaltarLinea("Alguno de los vertices no existe");
		}else Consola.escribirSaltarLinea("El coste debe ser mayor a cero o positivo");
	}
	
	public int[][] matrizPonderados() {
		int [][] matrizAdy = new int [listaVertices.getTamanio()][listaVertices.getTamanio()];
		NodoLista<NodoGrafo<T>> aux1 = listaVertices.getCabeza();
		Lista<NodoGrafo<T>> listTemp = listaVertices;
		NodoLista<NodoGrafo<T>> aux2 = new NodoLista<NodoGrafo<T>>();
		for (int i = 0; i < matrizAdy.length; i++) {
			aux2 = listTemp.getCabeza();
			for (int j = 0; j < matrizAdy.length; j++) {
					if (verificarArista(aux1.getDato().getElemento(), aux2.getDato().getElemento())) matrizAdy[i][j] = this.pesoArista(aux1.getDato(), aux2.getDato());
					else matrizAdy[i][j] = 0;
				aux2 = aux2.getSiguiente();
			}
			aux1 = aux1.getSiguiente();
		}
		return matrizAdy;
	}

	private int pesoArista(NodoGrafo<T> vInicial, NodoGrafo<T> vFinal) { return this.pesoArista(vInicial.getListaNodos().getCabeza(), vFinal); }

	private int pesoArista(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vFinal) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().equals(vFinal)) return aux.getCoste();
			return this.pesoArista(aux.getSiguiente(), vFinal);
		}
		return -1;
	}
	
	public void profundidad(T elemento){
		NodoGrafo<T> nodo = this.retornarVertice(elemento);
		if (nodo != null){
			if (nodo.existenAdyacencias()){
				ArrayList<NodoGrafo<T>> verVisitados = new ArrayList<NodoGrafo<T>>();
				NodoLista<NodoGrafo<T>> inicial = this.listaVertices.getCabeza();
				for (int i = 0; i < listaVertices.getTamanio(); i++){
					if (elemento.equals(inicial.getDato().getElemento())) break;
					inicial = inicial.getSiguiente();
				}
				this.profundidad(verVisitados, inicial, null);
				Consola.escribirSaltarLinea("\nVERTICE INICIAL: " + elemento);
				this.mostrarAmplitud(verVisitados);
			} else Consola.escribirSaltarLinea("No existen adyacencias");
		} else Consola.escribirSaltarLinea("No existe el vertice " + elemento + " en el grafo");
	}

	private void profundidad(ArrayList<NodoGrafo<T>> listaV, NodoLista<NodoGrafo<T>> inicial, NodoGrafo<T> anterior) {
		if (inicial != null) {
			if (listaV.size() == 0) {
				listaV.add(inicial.getDato());
				this.profundidad(listaV, inicial.getDato().getListaNodos().getCabeza(), inicial.getDato());
			} else {
				if (!this.existeEnLista(listaV,inicial.getDato().getElemento())) {
					listaV.add(anterior);
					listaV.add(inicial.getDato());
					this.profundidad(listaV, inicial.getDato().getListaNodos().getCabeza(), inicial.getDato());
				}
			}
			this.profundidad(listaV, inicial.getSiguiente(), anterior);
		}
	}

	private void mostrarAmplitud(ArrayList<NodoGrafo<T>> list) {
		Consola.escribirSaltarLinea("RECORRIDO EN PROFUNDIDAD:");
		for (int i = 1; i < list.size(); i++) {
			if (i == list.size() - 2) Consola.escribir("(" + list.get(i).getElemento() + "->" + list.get(i + 1).getElemento() + ")");
			else if (i % 2 == 1) Consola.escribir("(" + list.get(i).getElemento() + "->" + list.get(i + 1).getElemento() + "),");
		}
	}

	private boolean existeEnLista(ArrayList<NodoGrafo<T>> lista, T elemento) {
		for (int i = 0; i < lista.size(); i++) if (elemento.equals(lista.get(i).getElemento())) return true;
		return false;
	}

	public void anchura(T verticeInicial) {
		if (this.verificarVertice(verticeInicial)) {
			ArrayList<NodoGrafo<T>> verVisitados = new ArrayList<NodoGrafo<T>>();
			NodoLista<NodoGrafo<T>> elemento = this.listaVertices.getCabeza();
			for (int i = 0; i < this.listaVertices.getTamanio(); i++) {
				if (verticeInicial.equals(elemento.getDato().getElemento())) break;
				elemento = elemento.getSiguiente();
			}
			this.anchura(verVisitados, elemento);
			this.anchura(verVisitados, getListaVertices().getCabeza());
			for (int i = 0; i < verVisitados.size(); i++) Consola.escribir(verVisitados.get(i).getElemento() + " ");
		}
	}

	private void anchura(ArrayList<NodoGrafo<T>> listaV, NodoLista<NodoGrafo<T>> inicial) {
		if (!this.esNulo(inicial)) {
			if (listaV.size() == 0) {
				listaV.add(inicial.getDato());
				this.anchura(listaV, inicial.getDato().getListaNodos().getCabeza());
			} else {
				NodoLista<NodoGrafo<T>> aux = inicial.getDato().getListaNodos().getCabeza();
				do {
					if (!listaV.contains(inicial.getDato())) listaV.add(inicial.getDato());
					aux = aux.getSiguiente();
				} while (!this.esNulo(aux));
			}
			this.anchura(listaV, inicial.getSiguiente());
		}
	}
	
	private boolean verificaCiclo(NodoGrafo<T> inic, NodoGrafo<T> fin){
    	NodoLista<NodoGrafo<T>> aux = this.listaVertices.getCabeza().getSiguiente();
    	while (aux != null) {            
    		NodoGrafo<T> dato = aux.getDato();
    		if (dato.estaEnLista(inic.getElemento()) && dato.estaEnLista(fin.getElemento())) return true;
    		aux = aux.getSiguiente();
    	}
    	return false;
   	}
	
	private void kruskalVertices(NodoLista<NodoGrafo<T>> aux, Grafo<T> kruskal) {
    	if (!this.esNulo(aux)) {
    		kruskal.agregarVertice(aux.getDato().getElemento());
    		this.kruskalVertices(aux.getSiguiente(), kruskal);
    	}
    }
    	
	private List<Arista<T>> getListaAristas() {
    	Lista<NodoGrafo<T>> listTemp = this.listaVertices;
    	NodoLista<NodoGrafo<T>> aux = listTemp.getCabeza();
    	List<Arista<T>> list = new ArrayList<Arista<T>>();
    	while (aux != null) {
    		NodoLista<NodoGrafo<T>> aux2 = listTemp.getCabeza();
    		while (aux2 != null) {
				int peso = this.pesoArista(aux.getDato(), aux2.getDato());
				if (peso > 0) {
					list.add(new Arista<>(aux.getDato(), aux2.getDato(), peso));
					aux2.getDato().eliminarNodo(aux.getDato());
				}
				aux2 = aux2.getSiguiente();
			}
			aux = aux.getSiguiente();
		}
		return list;
	}
	
	public void methodKruskal() {
        	int sizeVertices = listaVertices.getTamanio();
        	int contador = 0, coste = 0;
	        List<Arista<T>> list = this.getListaAristas();
        	Collections.sort(list, new AristaSort<T>());
        	Grafo<T> kruskal = new Grafo<T>();
        	kruskalVertices(listaVertices.getCabeza(), kruskal);
        	System.out.print("T = ");
        	for (Arista<T> listaArista : list) {
        	    NodoGrafo<T> inic = listaArista.getInicial();
        	    NodoGrafo<T> fin = listaArista.getFin();
        	    if (!kruskal.verificaCiclo(inic, fin) && contador < (sizeVertices -1)) {
        	        kruskal.agregarArista(inic.getElemento(), fin.getElemento(), listaArista.getCoste());
        	        System.out.print("[" + inic.getElemento() + "-" + fin.getElemento() + "," + listaArista.getCoste() + "],");
        	        coste += listaArista.getCoste();
        	        contador++;
        	    }
        	}
        	System.out.println(" Longitud de camino = " + coste);
        	/**
        	 * Opcional utilizarlo
        	 */
        
        	//kruskal.longitudCamino();
    	}
    
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) { return nodo == null; }
}
