package Negocio;

public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() {
		this.raiz = null;
	}

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void insertar(Comparable<T> elemento) {
		raiz = insertar(raiz, elemento);
	}

	public int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null)
			return 0;
		else
			return (arbol.getFactorEquilibrio() + 1);
	}

	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho)
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}

	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) {
			arbol = new Nodo<T>((T) elemento, null, null);
		} else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.setIzq(insertar(arbol.getIzq(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo(arbol.getIzq().getElemento()) < 0)
					arbol = RotacionSimpleDer(arbol);
				else
					arbol = RotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo(arbol.getElemento()) > 0) {
			arbol.setDer(insertar(arbol.getDer(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
				if (elemento.compareTo(arbol.getDer().getElemento()) > 0)
					arbol = RotacionSimpleIzq(arbol);
				else
					arbol = RotacionDobleDer_Izq(arbol);
			}
		} else {
			;// En el caso de que sean valores duplicados no hace nada
		}
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		return arbol;
	}

	private Nodo<T> RotacionSimpleDer(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getIzq();
		arbol.setIzq(subArbol.getDer());
		subArbol.setDer(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getIzq()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private Nodo<T> RotacionSimpleIzq(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getDer();
		arbol.setDer(subArbol.getIzq());
		subArbol.setIzq(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getDer()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private Nodo<T> RotacionDobleIzq_Der(Nodo<T> arbol) {
		arbol.setIzq(RotacionSimpleIzq(arbol.getIzq()));
		return RotacionSimpleDer(arbol);
	}

	private Nodo<T> RotacionDobleDer_Izq(Nodo<T> subArbol) {
		subArbol.setDer(RotacionSimpleDer(subArbol.getDer()));
		return RotacionSimpleIzq(subArbol);
	}
	public void imprimirArbol(){
		imprimirArbol(raiz, 0);
	}
	private void imprimirArbol(Nodo<T> arbol,int nivel){
		if (arbol!=null) {
			imprimirArbol(arbol.getIzq(),nivel+1);
			System.out.println(" "+nivel+" "+arbol.getElemento());
			imprimirArbol(arbol.getDer(), nivel+1);
		}
	}
	public int funcionLucas(int n){
		if (n==0) {
			return 2;
		}else if (n==1) {
			return 1;
		}else{
			return funcionLucas(n-1)+funcionLucas(n-2);
		}
	}
	public int compararLucas(int elemento,int n){
		if (elemento==funcionLucas(n)) {
			return elemento;
		}else if(elemento>funcionLucas(n)){
			return compararLucas(elemento, n+1);
		}
		return -1;
	}
	public int mostrarLucas() {
		return mostrarLucas(raiz,0);
	}
	public int mostrarLucas(Nodo<T> arbol,int cont) {
		int lucas;
		if (arbol!=null) {
			cont=mostrarLucas(arbol.getIzq(),cont);
			lucas=compararLucas((int)arbol.getElemento(), 0);
			if (lucas!=-1) {
				System.out.print(" "+lucas);
				cont++;
			}
			cont=mostrarLucas(arbol.getDer(),cont);
		}
		return cont;
	}
	
	public boolean estaVacio() {
		if (raiz!=null) {
			return true;
		}
		return false;
	}
}
