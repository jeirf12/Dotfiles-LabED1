package Negocio;

public class GrafoBipartito <T>{
	private Lista<NodoGrafo<T>> listaVertices;
	private Lista<NodoGrafo<T>> conjunto1;
	private Lista<NodoGrafo<T>> conjunto2;
	
	public GrafoBipartito() {
		this.listaVertices=new Lista<NodoGrafo<T>>();
		this.conjunto1=new Lista<NodoGrafo<T>>();
		this.conjunto2=new Lista<NodoGrafo<T>>();
	}
	
	public Lista<NodoGrafo<T>> getListaVertices() {
		return listaVertices;
	}

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) {
		this.listaVertices = listaVertices;
	}

	public Lista<NodoGrafo<T>> getConjunto1() {
		return conjunto1;
	}

	public void setConjunto1(Lista<NodoGrafo<T>> conjunto1) {
		this.conjunto1 = conjunto1;
	}

	public Lista<NodoGrafo<T>> getConjunto2() {
		return conjunto2;
	}

	public void setConjunto2(Lista<NodoGrafo<T>> conjunto2) {
		this.conjunto2 = conjunto2;
	}
	
	private boolean verificarVertice(T vertice) {
		NodoLista<NodoGrafo<T>> aux=listaVertices.getCabeza();
		return verificarVertice(aux, vertice);
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
	
	public NodoGrafo<T> retornarVertice(T vertice){
		NodoLista<NodoGrafo<T>> aux=listaVertices.getCabeza();
		return retornarVertice(aux, vertice);
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
	
	public void agregarVertice(T vertice,int grupo) {
		if (!verificarVertice(vertice)) {
			NodoGrafo<T> vertNuevo=new NodoGrafo<T>(vertice);
			listaVertices.agregar(vertNuevo, 0);
			if (grupo==1) {
				conjunto1.agregar(vertNuevo, 0);
			}else if (grupo==2) {
				conjunto2.agregar(vertNuevo, 0);
			}
		}
	}
	
	private boolean buscarConjunto(NodoGrafo<T> vertice) {
		NodoLista<NodoGrafo<T>> aux=conjunto1.getCabeza();
		return buscarConjunto(aux, vertice);
	}
	private boolean buscarConjunto(NodoLista<NodoGrafo<T>> aux,NodoGrafo<T> vertice) {
		if (!esNulo(aux)) {
			if (aux.getDato().equals(vertice)) {
				return true;
			}
			return buscarConjunto(aux.getSiguiente(), vertice);
		}
		return false;
	}
	
	private boolean verificarArista(T vInicial,T vFinal) {
		if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
			NodoGrafo<T> vertInicial=retornarVertice(vInicial);
			if (!vInicial.equals(vFinal)) {
				NodoLista<NodoGrafo<T>> aux=vertInicial.getListaNodos().getCabeza();
				return verificarArista(aux, vFinal);
			}
		}
		return false;
	}
	private boolean verificarArista(NodoLista<NodoGrafo<T>> aux,T vFinal) {
		if (!esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vFinal)) {
				return true;
			}
			return verificarArista(aux.getSiguiente(), vFinal);
		}
		return false;
	}
	
	public void agregarArista(T vertInicial, T vertFinal,int peso) {
		if (peso>0) {
			if (verificarVertice(vertInicial) && verificarVertice(vertFinal)) {
				if (!vertInicial.equals(vertFinal)) {
					NodoGrafo<T> vInicial=retornarVertice(vertInicial),vFinal=retornarVertice(vertFinal);
					if ((buscarConjunto(vInicial) && !buscarConjunto(vFinal)) || (buscarConjunto(vFinal) && !buscarConjunto(vInicial))) {
						if (!verificarArista(vertInicial, vertFinal)) {
							vInicial.setListaNodos(vFinal, peso);
							vFinal.setListaNodos(vInicial, peso);
						}else {
							Consola.escribirSaltarLinea("La arista ya existe para agregarla");
						}
					}else {
						Consola.escribirSaltarLinea("Los vertices pertenecen al mismo grupo y no se puede agregar la arista");
					}
				}else {
					Consola.escribirSaltarLinea("No se permiten bucles en las aristas");
				}
			}else {
				Consola.escribirSaltarLinea("Uno de los vertices no se encuentran en el grafo");
			}
		}else {
			Consola.escribirSaltarLinea("El peso debe ser mayor a cero para agregar arista");
		}
	}
	public void eliminarArista(T vInicial, T vFinal) {
		if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
			if (!vInicial.equals(vFinal)) {
				if (verificarArista(vInicial, vFinal)) {
					NodoGrafo<T> vertInicial=retornarVertice(vInicial),vertFinal=retornarVertice(vFinal);
					vertInicial.getListaNodos().eliminar(vertFinal);
					vertFinal.getListaNodos().eliminar(vertInicial);
				}else {
					Consola.escribirSaltarLinea("No existe arista entre los dos vertices para eliminar");
				}
			}else {
				Consola.escribirSaltarLinea("Los dos vertices son iguales, no hay bucles de aristas para eliminar");
			}
		}else {
			Consola.escribirSaltarLinea("Al menos uno de sus vertices no existe para eliminar");
		}
	}
	
	public void modificarPesoArista(T vInicial,T vFinal,int peso) {
		if (peso>0) {
			if (verificarVertice(vInicial) && verificarVertice(vFinal)) {
				if (!vInicial.equals(vFinal)) {
					if (verificarArista(vInicial, vFinal)) {
						NodoGrafo<T> vertInicial=retornarVertice(vInicial),vertFinal=retornarVertice(vFinal);
						NodoLista<NodoGrafo<T>> aux=vertInicial.getListaNodos().getCabeza();
						modificarPesoArista(aux, vertInicial, vertFinal, peso);
					}else {
						Consola.escribirSaltarLinea("No existe arista entre los dos vertices para modificar el peso entre ellas");
					}
				}else {
					Consola.escribirSaltarLinea("Los dos vertices son iguales, no hay bucles de aristas para modificar el peso");
				}
			}else {
				Consola.escribirSaltarLinea("Al menos uno de sus vertices no existe para modificar el peso de la arista");
			}
		}else {
			Consola.escribirSaltarLinea("El peso debe ser mayor a cero para modificar el peso de la arista");
		}
	}
	private void modificarPesoArista(NodoLista<NodoGrafo<T>> aux,NodoGrafo<T> vInicial,NodoGrafo<T> vFinal,int peso) {
		if (!esNulo(aux)) {
			modificarPesoArista(aux, vFinal.getListaNodos().getCabeza(), vInicial, vFinal, peso);
			modificarPesoArista(aux.getSiguiente(), vInicial, vFinal,peso);
		}
	}
	private void modificarPesoArista(NodoLista<NodoGrafo<T>> aux,NodoLista<NodoGrafo<T>> aux2,NodoGrafo<T> vInicial,NodoGrafo<T> vFinal,int peso) {
		if (!esNulo(aux2)) {
			if (aux.getDato().equals(vFinal) && aux2.getDato().equals(vInicial)) {
				aux.setCoste(peso);
				aux2.setCoste(peso);
			}
			modificarPesoArista(aux, aux2.getSiguiente(), vInicial, vFinal, peso);
		}
	}
	
	private void mostrarConjuntos() {
		Consola.escribir("GV1 = { ");
		mostrarConjuntos(conjunto1.getCabeza());
		Consola.escribirSaltarLinea("}");
		Consola.escribir("GV2 = { ");
		mostrarConjuntos(conjunto2.getCabeza());
		Consola.escribirSaltarLinea("}");
	}
	private void mostrarConjuntos(NodoLista<NodoGrafo<T>> aux) {
		if(!esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento()+" ");
			mostrarConjuntos(aux.getSiguiente());
		}
	}
	
	private void mostrarAristas() {
		NodoLista<NodoGrafo<T>> aux=listaVertices.getCabeza();
		mostrarAristas(aux);
	}
	private void mostrarAristas(NodoLista<NodoGrafo<T>> aux) {
		if (!esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento()+" -> ");
			aux.getDato().mostrarAristas();
			mostrarAristas(aux.getSiguiente());
		}
	}
	
	public void mostrarGrafo() {
		mostrarConjuntos();
		mostrarAristas();
	}
	
	private void mostrarVerticesGrupo2() {
		Consola.escribir("   ");
		mostrarVerticesGrupo2(conjunto2.getCabeza());
		Consola.escribirSaltarLinea("");
	}
	private void mostrarVerticesGrupo2(NodoLista<NodoGrafo<T>> aux) {
		if(!esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento()+"  ");
			mostrarVerticesGrupo2(aux.getSiguiente());
		}
		
	}
	
	private int[][] matrizAdyacencias() {
		int [][]matriz=new int[conjunto1.getTamanio()][conjunto2.getTamanio()];
		NodoLista<NodoGrafo<T>> aux1 = conjunto1.getCabeza();
		Lista<NodoGrafo<T>> listTemp = conjunto2;
		NodoLista<NodoGrafo<T>> aux2 = new NodoLista<NodoGrafo<T>>();
		for (int i = 0; i < matriz.length; i++) {
			aux2=listTemp.getCabeza();
			for (int j = 0; j < matriz.length; j++) {
				if (verificarArista(aux1.getDato().getElemento(), aux2.getDato().getElemento())) {
					matriz[i][j]=1;
				}else {
					matriz[i][j]=0;
				}
				aux2=aux2.getSiguiente();
			}
			aux1=aux1.getSiguiente();
		}
		return matriz;
	}
	private void imprimirMatriz(int [][] matriz) {
		NodoLista<NodoGrafo<T>> aux1 = conjunto1.getCabeza();
		Consola.escribirSaltarLinea("La matriz de adyacencias del grafo es: ");
		mostrarVerticesGrupo2();
		for (int i = 0; i < matriz.length; i++) {
			Consola.escribir(aux1.getDato().getElemento()+"  ");
			for (int j = 0; j < matriz.length; j++) {
				Consola.escribir(matriz[i][j] + "  ");
			}
			Consola.escribirSaltarLinea("");
			aux1=aux1.getSiguiente();
		}
		Consola.escribirSaltarLinea("");
	}
	public void mostrarMatrizGrafoBipartito() {
		int [][] matriz=matrizAdyacencias();
		imprimirMatriz(matriz);
	}
	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) {
		return nodo==null;
	}
}
