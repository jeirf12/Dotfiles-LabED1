package Negocio;

public class GrafoBipartito <T>{
	private Lista<NodoGrafo<T>> listaVertices;
	private Lista<NodoGrafo<T>> conjunto1;
	private Lista<NodoGrafo<T>> conjunto2;
	
	public GrafoBipartito() { this.listaVertices = new Lista<NodoGrafo<T>>(); this.conjunto1 = new Lista<NodoGrafo<T>>(); this.conjunto2 = new Lista<NodoGrafo<T>>(); }
	
	public Lista<NodoGrafo<T>> getListaVertices() { return this.listaVertices; }

	public void setListaVertices(Lista<NodoGrafo<T>> listaVertices) { this.listaVertices = listaVertices; }

	public Lista<NodoGrafo<T>> getConjunto1() { return this.conjunto1; }

	public void setConjunto1(Lista<NodoGrafo<T>> conjunto1) { this.conjunto1 = conjunto1; }

	public Lista<NodoGrafo<T>> getConjunto2() { return this.conjunto2; }

	public void setConjunto2(Lista<NodoGrafo<T>> conjunto2) { this.conjunto2 = conjunto2; }
	
	private boolean verificarVertice(T vertice) { return this.verificarVertice(this.listaVertices.getCabeza(), vertice); }

	private boolean verificarVertice(NodoLista<NodoGrafo<T>> aux, T vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vertice)) return true;
			return this.verificarVertice(aux.getSiguiente(), vertice);
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
	
	public void agregarVertice(T vertice, int grupo) {
		if (!this.verificarVertice(vertice)) {
			NodoGrafo<T> vertNuevo = new NodoGrafo<T>(vertice);
			this.listaVertices.agregar(vertNuevo, 0);
			if (grupo == 1) this.conjunto1.agregar(vertNuevo, 0);
			else if (grupo == 2) this.conjunto2.agregar(vertNuevo, 0);
		}
	}
	
	private boolean buscarConjunto(NodoGrafo<T> vertice) { return this.buscarConjunto(this.conjunto1.getCabeza(), vertice); }

	private boolean buscarConjunto(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vertice) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().equals(vertice)) return true;
			return this.buscarConjunto(aux.getSiguiente(), vertice);
		}
		return false;
	}
	
	private boolean verificarArista(T vInicial,T vFinal) {
		if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
			NodoGrafo<T> vertInicial = this.retornarVertice(vInicial);
			if (!vInicial.equals(vFinal)) return verificarArista(vertInicial.getListaNodos().getCabeza(), vFinal);
		}
		return false;
	}

	private boolean verificarArista(NodoLista<NodoGrafo<T>> aux, T vFinal) {
		if (!this.esNulo(aux)) {
			if (aux.getDato().getElemento().equals(vFinal)) return true;
			return this.verificarArista(aux.getSiguiente(), vFinal);
		}
		return false;
	}
	
	public void agregarArista(T vertInicial, T vertFinal, int peso) {
		if (peso > 0) {
			if (this.verificarVertice(vertInicial) && this.verificarVertice(vertFinal)) {
				if (!vertInicial.equals(vertFinal)) {
					NodoGrafo<T> vInicial = this.retornarVertice(vertInicial), vFinal = this.retornarVertice(vertFinal);
					if ((this.buscarConjunto(vInicial) && !this.buscarConjunto(vFinal)) || (this.buscarConjunto(vFinal) && !this.buscarConjunto(vInicial))) {
						if (!this.verificarArista(vertInicial, vertFinal)) {
							vInicial.setListaNodos(vFinal, peso);
							vFinal.setListaNodos(vInicial, peso);
						} else Consola.escribirSaltarLinea("La arista ya existe para agregarla");
					} else Consola.escribirSaltarLinea("Los vertices pertenecen al mismo grupo y no se puede agregar la arista");
				} else Consola.escribirSaltarLinea("No se permiten bucles en las aristas");
			}else Consola.escribirSaltarLinea("Uno de los vertices no se encuentran en el grafo");
		}else Consola.escribirSaltarLinea("El peso debe ser mayor a cero para agregar arista");
	}

	public void eliminarArista(T vInicial, T vFinal) {
		if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
			if (!vInicial.equals(vFinal)) {
				if (this.verificarArista(vInicial, vFinal)) {
					NodoGrafo<T> vertInicial = this.retornarVertice(vInicial), vertFinal = this.retornarVertice(vFinal);
					vertInicial.getListaNodos().eliminar(vertFinal);
					vertFinal.getListaNodos().eliminar(vertInicial);
				} else Consola.escribirSaltarLinea("No existe arista entre los dos vertices para eliminar");
			}else Consola.escribirSaltarLinea("Los dos vertices son iguales, no hay bucles de aristas para eliminar");
		} else Consola.escribirSaltarLinea("Al menos uno de sus vertices no existe para eliminar");
	}
	
	public void modificarPesoArista(T vInicial, T vFinal, int peso) {
		if (peso > 0) {
			if (this.verificarVertice(vInicial) && this.verificarVertice(vFinal)) {
				if (!vInicial.equals(vFinal)) {
					if (this.verificarArista(vInicial, vFinal)) {
						NodoGrafo<T> vertInicial = this.retornarVertice(vInicial), vertFinal = this.retornarVertice(vFinal);
						this.modificarPesoArista(vertInicial.getListaNodos().getCabeza(), vertInicial, vertFinal, peso);
					} else Consola.escribirSaltarLinea("No existe arista entre los dos vertices para modificar el peso entre ellas");
				} else Consola.escribirSaltarLinea("Los dos vertices son iguales, no hay bucles de aristas para modificar el peso");
			}else Consola.escribirSaltarLinea("Al menos uno de sus vertices no existe para modificar el peso de la arista");
		}else Consola.escribirSaltarLinea("El peso debe ser mayor a cero para modificar el peso de la arista");
	}

	private void modificarPesoArista(NodoLista<NodoGrafo<T>> aux, NodoGrafo<T> vInicial, NodoGrafo<T> vFinal, int peso) {
		if (!this.esNulo(aux)) {
			this.modificarPesoArista(aux, vFinal.getListaNodos().getCabeza(), vInicial, vFinal, peso);
			this.modificarPesoArista(aux.getSiguiente(), vInicial, vFinal, peso);
		}
	}
	
	private void modificarPesoArista(NodoLista<NodoGrafo<T>> aux, NodoLista<NodoGrafo<T>> aux2, NodoGrafo<T> vInicial, NodoGrafo<T> vFinal, int peso) {
		if (!this.esNulo(aux2)) {
			if (aux.getDato().equals(vFinal) && aux2.getDato().equals(vInicial)) {
				aux.setCoste(peso);
				aux2.setCoste(peso);
			}
			this.modificarPesoArista(aux, aux2.getSiguiente(), vInicial, vFinal, peso);
		}
	}
	
	private void mostrarConjuntos() {
		Consola.escribir("GV1 = { ");
		this.mostrarConjuntos(this.conjunto1.getCabeza());
		Consola.escribirSaltarLinea("}");
		Consola.escribir("GV2 = { ");
		this.mostrarConjuntos(this.conjunto2.getCabeza());
		Consola.escribirSaltarLinea("}");
	}

	private void mostrarConjuntos(NodoLista<NodoGrafo<T>> aux) {
		if(!this.esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento() + " ");
			this.mostrarConjuntos(aux.getSiguiente());
		}
	}
	
	private void mostrarAristas() { this.mostrarAristas(this.listaVertices.getCabeza()); }

	private void mostrarAristas(NodoLista<NodoGrafo<T>> aux) {
		if (!this.esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento() + " -> ");
			aux.getDato().mostrarAristas();
			this.mostrarAristas(aux.getSiguiente());
		}
	}
	
	public void mostrarGrafo() {
		this.mostrarConjuntos();
		this.mostrarAristas();
	}
	
	private void mostrarVerticesGrupo2() {
		Consola.escribir("   ");
		this.mostrarVerticesGrupo2(this.conjunto2.getCabeza());
		Consola.escribirSaltarLinea("");
	}

	private void mostrarVerticesGrupo2(NodoLista<NodoGrafo<T>> aux) {
		if(!this.esNulo(aux)) {
			Consola.escribir(aux.getDato().getElemento() + "  ");
			this.mostrarVerticesGrupo2(aux.getSiguiente());
		}
	}
	
	private int[][] matrizAdyacencias() {
		int [][]matriz = new int[conjunto1.getTamanio()][conjunto2.getTamanio()];
		NodoLista<NodoGrafo<T>> aux1 = conjunto1.getCabeza();
		Lista<NodoGrafo<T>> listTemp = conjunto2;
		NodoLista<NodoGrafo<T>> aux2 = new NodoLista<NodoGrafo<T>>();
		for (int i = 0; i < matriz.length; i++) {
			aux2 = listTemp.getCabeza();
			for (int j = 0; j < matriz.length; j++) {
				if (verificarArista(aux1.getDato().getElemento(), aux2.getDato().getElemento())) matriz[i][j]=1;
				else matriz[i][j]=0;
				aux2 = aux2.getSiguiente();
			}
			aux1 = aux1.getSiguiente();
		}
		return matriz;
	}

	private void imprimirMatriz(int [][] matriz) {
		NodoLista<NodoGrafo<T>> aux1 = conjunto1.getCabeza();
		Consola.escribirSaltarLinea("La matriz de adyacencias del grafo es: ");
		this.mostrarVerticesGrupo2();
		for (int i = 0; i < matriz.length; i++) {
			Consola.escribir(aux1.getDato().getElemento() + "  ");
			for (int j = 0; j < matriz.length; j++) Consola.escribir(matriz[i][j] + "  ");
			Consola.escribirSaltarLinea("");
			aux1 = aux1.getSiguiente();
		}
		Consola.escribirSaltarLinea("");
	}

	public void mostrarMatrizGrafoBipartito() {
		int [][] matriz = this.matrizAdyacencias();
		this.imprimirMatriz(matriz);
	}

	private boolean esNulo(NodoLista<NodoGrafo<T>> nodo) { return nodo == null; }
}
