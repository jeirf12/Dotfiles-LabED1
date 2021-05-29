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
	}/*
	public Grafo<T> kruskal() {
		Grafo<T> grafoKrus= new Grafo<T>();
		int[][] matrizAdy= matrizPonderados();
		int[][] matrizKrus= new int[matrizAdy.length][matrizAdy.length];
		int fila= listaVertices.indexOf(listaVertices.getCabeza().getDato());
		ArrayList<Integer> columnasVisitadas= new ArrayList<Integer>();
		ArrayList<Integer> FilasVisitadas= new ArrayList<Integer>();
		kruskal(fila, columnasVisitadas, matrizAdy, matrizKrus,FilasVisitadas);
		//Agregar los Vertices
		for (int i = 0; i < matrizKrus.length; i++) {
			NodoGrafo<T> nuevo= listaVertices.devolverNodo(i).getDato();
			grafoKrus.agregarVertice(nuevo.getElemento());
		}
		//Agregar las Aristas
		for (int i = 0; i < matrizKrus.length; i++) {
			for (int j = 0; j < matrizKrus.length; j++) {
				if(matrizKrus[i][j]>0) {
					NodoGrafo<T> vInic= listaVertices.devolverNodo(i).getDato();
					NodoGrafo<T> vFina= listaVertices.devolverNodo(j).getDato();
					grafoKrus.agregarArista(vInic.getElemento(), vFina.getElemento(), matrizKrus[i][j]);
				}
			}
		}
		return grafoKrus;
	}
	private void kruskal(int fila, ArrayList<Integer>visitados,int[][] matrizAdy, int[][] matrizKrus, ArrayList<Integer> filasV) {
		if(visitados.size()<matrizAdy.length-1) {
			int[] pesos= new int[matrizAdy.length];
			for (int i = 0; i < matrizAdy.length; i++) {
				pesos[i]=matrizAdy[fila][i];
			}
			
			int posMenor=Integer.MAX_VALUE;
			int menor=Integer.MAX_VALUE; 
			for (int i = 0; i < pesos.length; i++) {
				if(pesos[i]>0) {
					if(pesos[i]<menor && !visitados.contains(i)) {
						menor=pesos[i];
						posMenor=i;
					}
				}
			}
			int menorVis=Integer.MAX_VALUE;
			int posMenorVis=Integer.MAX_VALUE;
			int filaMenorVis=Integer.MAX_VALUE;
			int [] temp= new int[pesos.length];
			for (int i = 0; i < filasV.size(); i++) {
				for (int j = 0; j < temp.length; j++) {
					temp[j]=matrizAdy[filasV.get(i)][j];
					if(temp[j]>0) {
						if(temp[j]<menorVis) {
							menorVis=temp[j];
							posMenorVis=j;
							filaMenorVis=filasV.get(i);
						}
					}
				}
			}
			if(menorVis<menor) {
				menor=menorVis;
				posMenor=posMenorVis;
				fila=filaMenorVis;
			}
			visitados.add(posMenor);
			filasV.add(fila);
			matrizKrus[fila][posMenor]=menor;
			matrizAdy[fila][posMenor]=0;
			matrizAdy[posMenor][fila]=0;
			kruskal(posMenor, visitados,matrizAdy,matrizKrus,filasV);
		}
	}*/
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
	
	private boolean existeArista(NodoGrafo<T> vInicial, NodoGrafo<T> vFinal) {
		if (vInicial.estaEnLista(vFinal.getElemento()) == true) {
			return true;
		} else {
			return false;
		}
	}/*
	public void mostrarkruskal() {
		Grafo<T> grafoKruskal=kruskal();
		grafoKruskal.longitudCamino();
	}*/
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
	public void kruskal() {
		int [][] matrizGrafoPonderado=matrizPonderados();
		Grafo<T> kruskal=new Grafo<T>();
		kruskal(listaVertices.getCabeza(), kruskal);
		kruskal(kruskal,matrizGrafoPonderado,0);
		kruskal.longitudCamino();
	}
	private void kruskal(NodoLista<NodoGrafo<T>> aux,Grafo<T> kruskal) {
		if (!esNulo(aux)) {
			kruskal.agregarVertice(aux.getDato().getElemento());
			kruskal(aux.getSiguiente(),kruskal);
		}
	}
	private void kruskal(Grafo<T> kruskal,int[][] matrizAdyacente,int fila) {
		if (fila<matrizAdyacente.length) {
			int menor=Integer.MAX_VALUE;
			int posicionMenor=0;
			int []pesos=new int[matrizAdyacente.length];
			for (int i = 0; i < pesos.length; i++) {
				pesos[i]=matrizAdyacente[fila][i];
			}
			for (int i = 0; i < pesos.length; i++) {
				if (pesos[i]>0) {
					if (pesos[i]<menor) {
						menor=pesos[i];
						posicionMenor=i;
					}
				}
			}
			matrizAdyacente[fila][posicionMenor]=0;
			NodoGrafo<T> inic=listaVertices.devolverNodo(fila).getDato();
			NodoGrafo<T> fin=listaVertices.devolverNodo(posicionMenor).getDato();
			if (!kruskal.verificarArista(inic.getElemento(), fin.getElemento())) {
				kruskal.agregarArista(inic.getElemento(), fin.getElemento(), menor);
			}
			kruskal(kruskal,matrizAdyacente,fila+1);
		}
	}
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
}
