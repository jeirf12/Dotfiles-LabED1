package Negocio;

import java.util.ArrayList;


public class Grafo<T> {
	// Atributos
	private Lista<NodoGrafo<T>> listaVertices;

	// Constructores
	public Grafo() {
		listaVertices = new Lista<NodoGrafo<T>>();
	}

	// Métodos getters and setters y los solicitados en la práctica
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

	public void verticesGrafoPonderado(){
		Consola.escribirSaltarLinea("------------------------");
		Consola.escribirSaltarLinea("Vertices grafo");
		Consola.escribirSaltarLinea("------------------------");
		verticesGrafoPonderado(listaVertices.getCabeza());
		Consola.escribirSaltarLinea("------------------------");
		
	}
	private void verticesGrafoPonderado(NodoLista<NodoGrafo<T>> aux) {
		if (!esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento()+" ->");
			aux.getDato().mostrarListaNodosGrafoPonderado();
			Consola.escribirSaltarLinea("");
			verticesGrafoPonderado(aux.getSiguiente());
		}
	}
	
	/**
     	* Logitud de camino con más proceso a la hora de imprimir (opcional utilizarlo)
     	*/
	public void longitudCamino() {
		int coste=0;
		Consola.escribir("T = ");
		coste=longitudCamino(listaVertices.getCabeza(), coste);
		Consola.escribirSaltarLinea(" Longitud de camino = "+coste);
	}
	private int longitudCamino(NodoLista<NodoGrafo<T>> aux,int coste) {
		if (!esNulo(aux)) {
			coste=longitudCamino(aux, aux.getDato().getListaNodos().getCabeza(), coste);
			coste=longitudCamino(aux.getSiguiente(),coste);
		}
		return coste;
	}
	private int longitudCamino(NodoLista<NodoGrafo<T>> aux,NodoLista<NodoGrafo<T>> aux2,int coste) {
		if (!esNulo(aux2)) {
			Consola.escribir("["+aux.getDato().getElemento()+"-"+aux2.getDato().getElemento()+","+aux2.getCoste()+"],");
			aux2.getDato().eliminarNodo(aux.getDato());
			coste+=aux2.getCoste();
			return longitudCamino(aux,aux2.getSiguiente(), coste);
		}
		return coste;
	}
	
	public boolean verificarVertice(T vertice){
		return verificarVertice(listaVertices.getCabeza(), vertice);
	}
	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux,T vertice) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) {
				return true;
			}
			return verificarVertice(aux.getSiguiente(),vertice);
		}
		return false;
	}
	
	public boolean verificarArista(T vInicial, T vFinal){
		if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
			NodoGrafo<T> verInicial = retornarVertice(vInicial);
			NodoGrafo<T> verFinal = retornarVertice(vFinal);
			if (verInicial!=null && verFinal!=null) {
				return verificarArista(verInicial.getListaNodos().getCabeza(), verFinal);
			}
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
	
	public void agregarArista(T vInicial,T vFinal,int coste){
		NodoGrafo<T> nodoI=null,nodoF=null;
		if (coste>0) {
			if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
				if (!vInicial.equals(vFinal)) {
					if (!verificarArista(vInicial, vFinal)) {
						nodoI=retornarVertice(vInicial);
						nodoF=retornarVertice(vFinal);
						nodoI.setListaNodos(nodoF,coste);
						nodoF.setListaNodos(nodoI,coste);
					}else{
						Consola.escribirSaltarLinea("La arista ya existe");
					}
				}else {
					Consola.escribirSaltarLinea("No se admite bucles");
				}
			}else {
				Consola.escribirSaltarLinea("Alguno de los vertices no existe");
			}
		}else {
			Consola.escribirSaltarLinea("El coste debe ser mayor a cero o positivo");
		}
	}
	
	public int[][] matrizPonderados() {
		int [][] matrizAdy = new int [listaVertices.getTamanio()][listaVertices.getTamanio()];
		NodoLista<NodoGrafo<T>> aux1 = listaVertices.getCabeza();
		Lista<NodoGrafo<T>> listTemp = listaVertices;
		NodoLista<NodoGrafo<T>> aux2 = new NodoLista<NodoGrafo<T>>();
		for (int i = 0; i < matrizAdy.length; i++) {
			aux2 = listTemp.getCabeza();
			for (int j = 0; j < matrizAdy.length; j++) {
					if (existeArista(aux1.getDato(), aux2.getDato())) {
						matrizAdy[i][j] =pesoArista(aux1.getDato(),aux2.getDato());
					} else {
						matrizAdy[i][j] = 0;
					}	
				aux2 = aux2.getSiguiente();
			}
			aux1 = aux1.getSiguiente();
		}
		return matrizAdy;
	}
	private int pesoArista(NodoGrafo<T> vInicial, NodoGrafo<T> vFinal) {
		return pesoArista(vInicial.getListaNodos().getCabeza(), vFinal);
	}
	private int pesoArista(NodoLista<NodoGrafo<T>> aux,NodoGrafo<T> vFinal) {
		if (!esNulo(aux)) {
			if (aux.getDato().equals(vFinal)) {
				return aux.getCoste();
			}
			return pesoArista(aux.getSiguiente(), vFinal);
		}
		return -1;
	}
	
	public void profundidad(T elemento){
		NodoGrafo<T> nodo = retornarVertice(elemento);
		if (nodo != null){
			if (nodo.existenAdyacencias()){
				ArrayList<NodoGrafo<T>> verVisitados = new ArrayList<NodoGrafo<T>>();
				NodoLista<NodoGrafo<T>> inicial = listaVertices.getCabeza();
				for (int i = 0; i < listaVertices.getTamanio(); i++){
					if (elemento.equals(inicial.getDato().getElemento())){
						break;
					}
					inicial = inicial.getSiguiente();
				}
				profundidad(verVisitados, inicial, null);
				Consola.escribirSaltarLinea("\nVERTICE INICIAL: " + elemento);
				mostrarAmplitud(verVisitados);
			} else {
				Consola.escribirSaltarLinea("No existen adyacencias");
			}
		} else{
			Consola.escribirSaltarLinea("No existe el vertice " + elemento + " en el grafo");
		}
	}
	private void profundidad(ArrayList<NodoGrafo<T>> listaV, NodoLista<NodoGrafo<T>> inicial, NodoGrafo<T> anterior) {
		if (inicial != null) {
			if (listaV.size() == 0) {
				listaV.add(inicial.getDato());
				profundidad(listaV, inicial.getDato().getListaNodos().getCabeza(), inicial.getDato());
			} else {
				if (!existeEnLista(listaV,inicial.getDato().getElemento())) {
					listaV.add(anterior);
					listaV.add(inicial.getDato());
					profundidad(listaV, inicial.getDato().getListaNodos().getCabeza(), inicial.getDato());
				}
			}
			profundidad(listaV, inicial.getSiguiente(), anterior);
		}
	}
	private void mostrarAmplitud(ArrayList<NodoGrafo<T>> list) {
		Consola.escribirSaltarLinea("RECORRIDO EN PROFUNDIDAD:");
		for (int i = 1; i < list.size(); i++) {
			if (i == list.size() - 2) {
				Consola.escribir("(" + list.get(i).getElemento() + "->" + list.get(i + 1).getElemento() + ")");
			} else if (i % 2 == 1) {
				Consola.escribir("(" + list.get(i).getElemento() + "->" + list.get(i + 1).getElemento() + "),");
			}
		}
	}
	private boolean existeEnLista(ArrayList<NodoGrafo<T>> lista, T elemento) {
		for (int i = 0; i < lista.size(); i++) {
			if (elemento.equals(lista.get(i).getElemento())) {
				return true;
			}
		}
		return false;
	}

	public void anchura(T verticeInicial) {
		if (verificarVertice(verticeInicial)) {
			ArrayList<NodoGrafo<T>> verVisitados = new ArrayList<NodoGrafo<T>>();
			NodoLista<NodoGrafo<T>> elemento = getListaVertices().getCabeza();
			for (int i = 0; i < listaVertices.getTamanio(); i++) {
				if (verticeInicial.equals(elemento.getDato().getElemento())) {
					break;
				}
				elemento = elemento.getSiguiente();
			}
			anchura(verVisitados, elemento);
			anchura(verVisitados, getListaVertices().getCabeza());
			for (int i = 0; i < verVisitados.size(); i++) {
				Consola.escribir(verVisitados.get(i).getElemento() + " ");
			}
		}

	}
	private void anchura(ArrayList<NodoGrafo<T>> listaV, NodoLista<NodoGrafo<T>> inicial) {
		if (!esNulo(inicial)) {
			if (listaV.size() == 0) {
				listaV.add(inicial.getDato());
				anchura(listaV, inicial.getDato().getListaNodos().getCabeza());
			} else {
				NodoLista<NodoGrafo<T>> aux = inicial.getDato().getListaNodos().getCabeza();
				do {
					if (!listaV.contains(inicial.getDato())) {
						listaV.add(inicial.getDato());
					}
					aux=aux.getSiguiente();
				} while (!esNulo(aux));
			}
			anchura(listaV, inicial.getSiguiente());
		}
	}
	
	private boolean verificaCiclo(NodoGrafo<T> inic, NodoGrafo<T> fin){
        	NodoLista<NodoGrafo<T>> aux = listaVertices.getCabeza().getSiguiente();
        	while (aux != null) {            
        	    NodoGrafo<T> dato = aux.getDato();
        	    if (dato.estaEnLista(inic.getElemento()) && dato.estaEnLista(fin.getElemento())) {
        	        return true;
        	    }
        	    aux = aux.getSiguiente();
        	}
        	return false;
    	}
	
	private void kruskalVertices(NodoLista<NodoGrafo<T>> aux, Grafo<T> kruskal) {
        	if (!esNulo(aux)) {
        	    kruskal.agregarVertice(aux.getDato().getElemento());
        	    kruskalVertices(aux.getSiguiente(), kruskal);
        	}
    	}
    	
	private List<Arista<T>> getListaAristas(){
        	Lista<NodoGrafo<T>> listTemp = listaVertices;
        	NodoLista<NodoGrafo<T>> aux = listTemp.getCabeza();
        	List<Arista<T>> list = new ArrayList<Arista<T>>();
        	while (aux != null) {
        	    NodoLista<NodoGrafo<T>> aux2 = listTemp.getCabeza();
        	    while (aux2 != null) {
        	        int peso = pesoArista(aux.getDato(), aux2.getDato());
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
	        List<Arista<T>> list = getListaAristas();
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
    
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
}
