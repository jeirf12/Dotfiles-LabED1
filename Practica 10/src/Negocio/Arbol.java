package Negocio;

public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	private int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null) return 0;
		else return arbol.getFactorEquilibrio() + 1;
	}

	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho) return alturaIzquierdo;
		else return alturaDerecho;
	}

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

	public void imprimirArbol() { this.imprimirArbol(this.raiz, 0); }

	private void imprimirArbol(Nodo<T> arbol, int nivel){
		if (arbol != null) {
			this.imprimirArbol(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.imprimirArbol(arbol.getDer(), nivel + 1);
		}
	}

	public void funcionLucasPrimo(int elemento) { this.funcionLucasPrimo(elemento, 0, 0, 0); }

	private void funcionLucasPrimo(int elemento, int contador, int contador2, int lucas) {
		if (elemento > contador || elemento > contador2) {
			lucas = this.funcionLucas(contador);
			if (this.validaPrimoLucas(lucas, 1, 0)) { System.out.println(" " + lucas); contador2++; }
			funcionLucasPrimo(elemento, contador + 1, contador2, lucas);
		}
	}
	
	private void compararLucas(int elemento, int n, int contador){
		if (elemento == this.funcionLucas(n)) {
			if (this.validaPrimoLucas(elemento, 1, 0)) System.out.println(" " + elemento + " Pertenece a la secuencia y es el termino " + (contador + 1));
		} else if(elemento > this.funcionLucas(n)) this.compararLucas(elemento, n + 1, contador + 1);
	}

	public void mostrarLucasArbol() { this.mostrarLucasArbol(this.raiz); }

	private void mostrarLucasArbol(Nodo<T> arbol) {
		if (arbol != null) {
			this.mostrarLucasArbol(arbol.getIzq());
			this.compararLucas((int)arbol.getElemento(), 0, 0);
			this.mostrarLucasArbol(arbol.getDer());
		}
	}

	private boolean validaPrimoLucas(int lucas,int n,int cont) {
		if (n <= lucas) {
			if (lucas % n == 0) return this.validaPrimoLucas(lucas, n + 1, cont + 1);
			return this.validaPrimoLucas(lucas, n + 1, cont);
		}
		return cont == 2;
	}
	
	private int funcionLucas(int n) {
		if (n == 0) return 2;
		else if (n == 1) return 1;
		else return this.funcionLucas(n - 1) + this.funcionLucas(n - 2);
	}

	public boolean esVacio() { return this.raiz == null; }
}
