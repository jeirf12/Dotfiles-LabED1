package Negocio;


public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	public void insertar(Comparable<T> elemento) { this.raiz = this.insertar(this.raiz, elemento); }

	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) arbol = new Nodo<T>((T) elemento);
		else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.setIzq(this.insertar(arbol.getIzq(), elemento));
			if (this.factorEquilibrio(arbol.getDer()) - this.factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo(arbol.getIzq().getElemento()) < 0) arbol = this.rotacionSimpleDer(arbol);
				else arbol = this.rotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo(arbol.getElemento()) > 0) {
			arbol.setDer(this.insertar(arbol.getDer(), elemento));
			if (this.factorEquilibrio(arbol.getDer()) - this.factorEquilibrio(arbol.getIzq()) == 2) {
				if (elemento.compareTo(arbol.getDer().getElemento()) > 0) arbol = this.rotacionSimpleIzq(arbol);
				else arbol = this.rotacionDobleDer_Izq(arbol);
			}
		} else {
			;// En el caso de que sean valores duplicados no hace nada
		}
		arbol.setFactorEquilibrio(this.mFE(this.factorEquilibrio(arbol.getIzq()), this.factorEquilibrio(arbol.getDer())));
		return arbol;
	}

	//Devuelve el valor de equilibrio de un arbol desequilibrado o equilibrado
	private int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null) return 0;
		else return arbol.getFactorEquilibrio() + 1;
	}

	//Determina cuando un arbol esta desequilibrado o equilibrado en alguna de sus ramas
	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho) return alturaIzquierdo;
		else return alturaDerecho;
	}

	//Rotaciones del arbol Simple Derecho,Izquierdo y Doble Derecho,Izquierda
	private Nodo<T> rotacionSimpleDer(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getIzq();
		arbol.setIzq(subArbol.getDer());
		subArbol.setDer(arbol);
		arbol.setFactorEquilibrio(this.mFE(this.factorEquilibrio(arbol.getIzq()), this.factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(this.mFE(this.factorEquilibrio(subArbol.getIzq()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private Nodo<T> rotacionSimpleIzq(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getDer();
		arbol.setDer(subArbol.getIzq());
		subArbol.setIzq(arbol);
		arbol.setFactorEquilibrio(this.mFE(this.factorEquilibrio(arbol.getIzq()), this.factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(this.mFE(this.factorEquilibrio(subArbol.getDer()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private Nodo<T> rotacionDobleIzq_Der(Nodo<T> arbol) {
		arbol.setIzq(this.rotacionSimpleIzq(arbol.getIzq()));
		return this.rotacionSimpleDer(arbol);
	}

	private Nodo<T> rotacionDobleDer_Izq(Nodo<T> subArbol) {
		subArbol.setDer(this.rotacionSimpleDer(subArbol.getDer()));
		return this.rotacionSimpleIzq(subArbol);
	}

	//Imprime todo el arbol
	public void imprimirArbol() { this.imprimirArbol(this.raiz, 0); }

	private void imprimirArbol(Nodo<T> arbol, int nivel){
		if (arbol != null) {
			this.imprimirArbol(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.imprimirArbol(arbol.getDer(), nivel + 1);
		}
	}

	//Muestra los recorridos preorden, inorden, postorden
	public void recorridos() {
		this.preorden();
		this.inorden();
		this.postorden();
	}

	//Muestra recorrido en preorden
	public void preorden(){
		System.out.println("\n Recorrido preorden ");
		this.preorden(this.raiz);
	}

	private void preorden(Nodo<T> arbol){
		if (arbol != null) {
			System.out.println(arbol.getElemento() + " ");
			this.preorden(arbol.getIzq());
			this.preorden(arbol.getDer());
		}
	}

	//Muestra recorrido en inorden
	private void inorden(){
		System.out.println("\n Recorrido inorden ");
		this.inorden(this.raiz);
	}

	private void inorden(Nodo<T> arbol){
		if (arbol != null) {
			this.inorden(arbol.getIzq());
			System.out.println(arbol.getElemento() + " ");
			this.inorden(arbol.getDer());
		}
	}

	//Muestra recorrido en postOrden
	private void postorden(){
		System.out.println("\n Recorrido postorden ");
		this.postorden(this.raiz);
	}

	private void postorden(Nodo<T> arbol){
		if (arbol != null) {
			this.postorden(arbol.getIzq());
			this.postorden(arbol.getDer());
			System.out.println(arbol.getElemento() + " ");
		}
	}

	//Muestra el peso del arbol, contando sus hojas con el metodo contarHojas
	public void pesoArbol() { System.out.println("El peso del arbol es: " + this.contarHojas(this.raiz, 0)); }

	//Cuenta los nodos que son hojas
	private int contarHojas(Nodo<T> arbol, int cont) {
		if (arbol != null) {
			if (this.esHoja(arbol)) cont++;
			cont = this.contarHojas(arbol.getIzq(), cont);
			cont = this.contarHojas(arbol.getDer(), cont);
		}
		return cont;
	}

	//Valida si un nodo es hoja retornando true o false
	private boolean esHoja(Nodo<T> arbol) {
		if (arbol.getIzq() == null && arbol.getDer() == null) return true;
		return false;
	}

	//Saca el valor del nivel de un dato
	public int nivelDeUnDato(Comparable<T> n) { return this.nivelDeUnNodo(raiz, n, 0, -1); }

	private int nivelDeUnNodo(Nodo<T> arbol, Comparable<T> n, int nivel, int nivelDato) {
		if (arbol != null) {
			nivelDato = this.nivelDeUnNodo(arbol.getIzq(), n, nivel + 1, nivelDato);
			if (n.equals(arbol.getElemento())) nivelDato = nivel;
			nivelDato = this.nivelDeUnNodo(arbol.getDer(), n, nivel + 1, nivelDato);
		}
		return nivelDato;
	}
	
	public int contarNodosNivel(int n) { return this.contarNodosNivel(this.raiz, n, 0, 0); }

	private int contarNodosNivel(Nodo<T> arbol, int n, int nivel, int cont) {
		if (arbol != null) {
			cont = this.contarNodosNivel(arbol.getIzq(), n, nivel + 1, cont);
			if (n == nivel) cont++;
			cont = this.contarNodosNivel(arbol.getDer(), n, nivel + 1, cont);
		}
		return cont;
	}
	
	public int nivelesDelArbol() { return this.nivelesDelArbol(this.raiz, 0); }

	private int nivelesDelArbol(Nodo<T> arbol, int n) {
		if (arbol != null) {
			if (arbol.getIzq() != null) return this.nivelesDelArbol(arbol.getIzq(), n + 1);
			return this.nivelesDelArbol(arbol.getDer(), n + 1);
		}
		return n - 1;
	}
	
	public void longitudCamino() { System.out.println("LCI: " + this.longitudCamino(this.raiz, 0, 0)); }

	private int longitudCamino(Nodo<T> arbol, int nivel, int lci) {
		int numNodos = this.contarNodosNivel(nivel);
		if (arbol != null) {
			lci = this.longitudCamino(arbol.getIzq(), nivel + 1, lci);
			if (numNodos > 0) return lci += (numNodos * (nivel + 1));
			lci = this.longitudCamino(arbol.getDer(), nivel + 1, lci);
		}
		return lci += (numNodos * (nivel + 1));
	}

	private void nodosNivelReversa(Nodo<T> arbol, int n, int nivel) {
		if (arbol != null) {
			this.nodosNivelReversa(arbol.getDer(), n, nivel + 1);
			if (n == nivel) System.out.print(arbol.getElemento() + "  ");
			this.nodosNivelReversa(arbol.getIzq(), n, nivel + 1);
		}
	}

	private void nodosNivel(Nodo<T> arbol, int n, int nivel) {
		if (arbol != null) {
			this.nodosNivel(arbol.getIzq(), n, nivel + 1);
			if (n == nivel) System.out.print(arbol.getElemento() + "  ");
			this.nodosNivel(arbol.getDer(), n, nivel + 1);
		}
	}

	public void imprimirZigZag() { this.imprimirZigZag(0); }

	private void imprimirZigZag(int nivel) {
		if (nivel == 0) {
			this.nodosNivel(this.raiz, nivel, 0);
			this.imprimirZigZag(nivel + 1);
		} else {
			if (nivel != this.nivelesDelArbol() + 1) {
				if (nivel % 2 == 0) {
					this.nodosNivelReversa(raiz, nivel, 0);
					this.imprimirZigZag(nivel + 1);
				} else {
					this.nodosNivel(raiz, nivel, 0);
					this.imprimirZigZag(nivel + 1);
				}
			}
		}
	}

	public boolean esVacio() { return this.raiz == null; }
}
