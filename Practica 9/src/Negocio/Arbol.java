package Negocio;

public class Arbol <T> {
	private Nodo<T> raiz;

	public Arbol() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }


	public int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null) return 0;
		else return (arbol.getFactorEquilibrio() + 1);
	}

	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho) return alturaIzquierdo;
		else return alturaDerecho;
	}

	public void insertar(Comparable<T> elemento) { this.raiz = this.insertar(this.raiz, elemento); }

	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) arbol = new Nodo<T>((T) elemento, null, null);
		else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.setIzq(insertar(arbol.getIzq(), elemento));
			if (this.factorEquilibrio(arbol.getDer()) - this.factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo(arbol.getIzq().getElemento()) < 0) arbol = this.rotacionSimpleDer(arbol);
				else arbol = this.rotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo(arbol.getElemento()) > 0) {
			arbol.setDer(insertar(arbol.getDer(), elemento));
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

	public void imprimirArbol(){ this.imprimirArbol(this.raiz, 0); }

	private void imprimirArbol(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			this.imprimirArbol(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.imprimirArbol(arbol.getDer(), nivel + 1);
		}
	}

	private int funcionLucas(int n) {
		if (n == 0) return 2;
		else if (n == 1) return 1;
		else return this.funcionLucas(n - 1) + this.funcionLucas(n - 2);
	}

	private int compararLucas(int elemento, int n) {
		if (elemento == this.funcionLucas(n)) return elemento;
		else if(elemento > this.funcionLucas(n)) return this.compararLucas(elemento, n + 1);
		return -1;
	}

	public int mostrarLucas() { return this.mostrarLucas(this.raiz,0); }

	private int mostrarLucas(Nodo<T> arbol, int cont) {
		if (arbol != null) {
			cont = this.mostrarLucas(arbol.getIzq(), cont);
			int lucas = this.compararLucas((int)arbol.getElemento(), 0);
			if (lucas != -1) {
				System.out.print(" " + lucas);
				cont++;
			}
			cont = this.mostrarLucas(arbol.getDer(), cont);
		}
		return cont;
	}
	
	public boolean estaVacio() { return this.raiz == null; }
}
