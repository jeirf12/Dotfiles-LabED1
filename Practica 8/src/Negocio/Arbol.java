package Negocio;

public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	public void setRaiz(Nodo<T> raiz) { this.raiz = raiz; }

	@SuppressWarnings("unchecked")
	public void insertar(T elemento) { this.raiz = this.raiz == null ? new Nodo<T>(elemento) : this.raiz.insertarNodo(this.raiz, (Comparable<T>) elemento); }

	public void imprimirArbolNiveles() { this.imprimirArbolNiveles(this.raiz, 0); }

	private void imprimirArbolNiveles(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			this.imprimirArbolNiveles(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.imprimirArbolNiveles(arbol.getDer(), nivel + 1);
		}
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

	public void nivelDeUnDato(Comparable<T> n) { this.nivelDeUnNodo(this.raiz, n, 0); }

	private void nivelDeUnNodo(Nodo<T> arbol, Comparable<T> n, int nivel) {
		if (arbol != null) {
			this.nivelDeUnNodo(arbol.getIzq(), n, nivel + 1);
			if (n.compareTo(arbol.getElemento()) == 0) System.out.println(nivel + " ");
			this.nivelDeUnNodo(arbol.getDer(), n, nivel + 1);
		}
	}

	public void mostrarFibonacci() { this.mostrarFibonacci(this.raiz); }

	private void mostrarFibonacci(Nodo<T> arbol) {
		if (arbol != null) {
			this.mostrarFibonacci(arbol.getIzq());
			int fibonacci = this.compararFibonacci((int)arbol.getElemento(), 0);
			if (fibonacci != -1) System.out.println(fibonacci);
			this.mostrarFibonacci(arbol.getDer());
		}
	}
	
	private int compararFibonacci(int elemento, int n) {
		if (elemento == this.fibonacci(n)) return elemento;
		else if(elemento > this.fibonacci(n)) return this.compararFibonacci(elemento, n + 1);
		return -1;
	}

	private int fibonacci(int n) {
		if (n < 2) return n;
		return this.fibonacci(n - 1) + this.fibonacci(n - 2);
	}

	public int alturaArbol() { return this.alturaArbol(this.raiz, 1, 0); }

	private int alturaArbol(Nodo<T> arbol, int nivel, int altura) {
		if (arbol != null) {
			altura = this.alturaArbol(arbol.getIzq(), nivel + 1, altura);
			if (nivel > altura) altura = nivel;
			altura = this.alturaArbol(arbol.getDer(), nivel + 1, altura);
		}
		return altura;
	}

	public int retornaNivelPodar() { return this.retornaNivelPodar(nivelesDelArbol()); }

	private int retornaNivelPodar(int nivel) {
		if (this.contarNodosNivel(nivel) < this.nivelesDelArbol() + 1) return nivel;
		return this.retornaNivelPodar(nivel - 1);
	}

	public void podar(int nivelPodar) { this.podar(this.raiz, 0, nivelPodar); }

	private void podar(Nodo<T> arbol, int nivel, int nivelPodar) {
		if (arbol != null) {
			podar(arbol.getIzq(), nivel + 1, nivelPodar);
			if (nivel == nivelPodar - 1) {
				arbol.setIzq(null);
				arbol.setDer(null);
			}
			podar(arbol.getDer(), nivel + 1, nivelPodar);
		}
	}
}
