package Negocio;

public class Arbol <T> {
	private Nodo<T> raiz;

	public Arbol() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	public void setRaiz(Nodo<T> raiz) { this.raiz = raiz; }

	@SuppressWarnings("unchecked")
	public void insertar(T elemento) {
		if (this.raiz == null) this.raiz = new Nodo<T>((T) elemento);
		else raiz = raiz.insertarNodo(raiz, (Comparable<T>) elemento);
	}

	public void imprimirArbolNiveles() {
		System.out.println("Recorrido por niveles");
		this.imprimirArbolNiveles(this.raiz, 0);
	}

	private void imprimirArbolNiveles(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			this.imprimirArbolNiveles(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.imprimirArbolNiveles(arbol.getDer(), nivel + 1);
		}
	}

	public void recorridos() {
		this.preorden();
		this.inorden();
		this.postorden();
		System.out.println();
	}

	private void preorden() {
		System.out.println("\n Recorrido preorden");
		this.preorden(this.raiz);
	}

	private void preorden(Nodo<T> arbol) {
		if (arbol != null) {
			System.out.print(arbol.getElemento() + " ");
			this.preorden(arbol.getIzq());
			this.preorden(arbol.getDer());
		}
	}

	private void inorden() {
		System.out.println("\n Recorrido inorden");
		this.inorden(this.raiz);
	}

	private void inorden(Nodo<T> arbol) {
		if (arbol != null) {
			this.inorden(arbol.getIzq());
			System.out.print(arbol.getElemento() + " ");
			this.inorden(arbol.getDer());
		}
	}

	private void postorden() {
		System.out.println("\n Recorrido postorden");
		this.postorden(this.raiz);
	}

	private void postorden(Nodo<T> arbol) {
		if (arbol != null) {
			this.postorden(arbol.getIzq());
			this.postorden(arbol.getDer());
			System.out.print(arbol.getElemento() + " ");
		}
	}

	public void mayorElemento() {
		Nodo<T> mayor = this.mayorElemento(this.raiz);
		int nivelMayor = this.nivelNodo(mayor.getElemento());
		System.out.println("\n Mayor Elemento " + mayor.getElemento() + " y esta en el nivel: " + nivelMayor + "\n");
	}

	private Nodo<T> mayorElemento(Nodo<T> arbol) {
		if (arbol.getDer() != null) return this.mayorElemento(arbol.getDer());
		return arbol;
	}

	public void menorElemento() {
		Nodo<T> menor = this.menorElemento(this.raiz);
		int nivelMenor = this.nivelNodo(menor.getElemento());
		System.out.println("\n menor Elemento " + menor.getElemento() + " y esta en el nivel: " + nivelMenor + "\n");
	}

	private Nodo<T> menorElemento(Nodo<T> arbol) {
		if (arbol.getIzq() != null) return this.menorElemento(arbol.getIzq());
		return arbol;
	}

	public void nodosNivel(int n) { this.nodosNivel(this.raiz, n, 0); }

	private void nodosNivel(Nodo<T> arbol, int n, int nivel) {
		if (arbol != null) {
			this.nodosNivel(arbol.getIzq(), n, nivel + 1);
			if (n == nivel) System.out.print(arbol.getElemento() + " ");
			this.nodosNivel(arbol.getDer(), n, nivel + 1);
		}
	}

	public int nivelNodo(T dato) { return this.nivelNodo(this.raiz, dato, 0, -1); }

	private int nivelNodo(Nodo<T> arbol,T dato, int nivel, int nivelDato) {
		if (arbol != null) {
			nivelDato = this.nivelNodo(arbol.getIzq(), dato, nivel + 1, nivelDato);
			if (dato == arbol.getElemento()) nivelDato = nivel;
			nivelDato = this.nivelNodo(arbol.getDer(), dato, nivel + 1, nivelDato);
		}
		return nivelDato;
	}

	public int niveles() { return this.niveles(this.raiz, 0); }

	private int niveles(Nodo<T> arbol, int n){
		if (arbol != null) {
			if (arbol.getIzq() != null) return this.niveles(arbol.getIzq(), n + 1);
			else return this.niveles(arbol.getDer(), n + 1);
		}
		return n - 1;
	}
}
